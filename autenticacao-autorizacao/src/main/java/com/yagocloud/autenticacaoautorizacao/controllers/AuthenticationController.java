package com.yagocloud.autenticacaoautorizacao.controllers;

import com.yagocloud.autenticacaoautorizacao.domain.users.AuthenticationDTO;
import com.yagocloud.autenticacaoautorizacao.domain.users.LoginTokenDTO;
import com.yagocloud.autenticacaoautorizacao.domain.users.RegisterUserDTO;
import com.yagocloud.autenticacaoautorizacao.domain.users.User;
import com.yagocloud.autenticacaoautorizacao.infra.security.TokenService;
import com.yagocloud.autenticacaoautorizacao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity login(@RequestBody AuthenticationDTO authenticationDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginTokenDTO(token));

    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody RegisterUserDTO userDTO) {
        if (this.userRepository.findByLogin(userDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());

        var newUser = new User(userDTO.login(), encryptedPassword, userDTO.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteItem(@PathVariable String id) {
        userRepository.deleteById(id);
    }
}
