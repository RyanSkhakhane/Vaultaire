package com.vaultaire.PasswordVault.jpa;

import com.vaultaire.PasswordVault.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, Long> {
}
