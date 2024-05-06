package com.vaultaire.PasswordVault.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<User> addUser( @RequestBody User user){
        User addU

    }

}
