package com.vaultaire.PasswordVault;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.vaultaire.PasswordVault.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Entity
public class PasswordsDAO {
    @JsonProperty
    @JsonIgnore
    @Id
    @GeneratedValue
    private Long pwdId;

    @JsonProperty
    private String accountName;

    @JsonProperty
    private String password;

    public Long getPwdId() {
        return pwdId;
    }

    public void setPwdId(Long pwdId) {
        this.pwdId = pwdId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        String SECRET_KEY = "VAULTAIRE"+accountName;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB?PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(),"AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedPassword = Base64.getDecoder().decode(this.password);
            String decryptedPassword = new String(cipher.doFinal(decodedPassword), StandardCharsets.UTF_8);
            return decryptedPassword;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void setPassword(String password) {
        String SECRET_KEY = "VAULTAIRE"+accountName;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB?PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(),"AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            String encryptedPassword = Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
            this.password = encryptedPassword;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
