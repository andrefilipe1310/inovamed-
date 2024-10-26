package com.inovamed.clinical_study_system.controller;

import com.inovamed.clinical_study_system.model.user.AutenticateDTO;
import com.inovamed.clinical_study_system.model.user.LoginResponseDTO;
import com.inovamed.clinical_study_system.model.user.RegisterDTO;
import com.inovamed.clinical_study_system.model.user.User;
import com.inovamed.clinical_study_system.repository.UserRepository;
import com.inovamed.clinical_study_system.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AutenticateDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity resgister(@RequestBody @Validated RegisterDTO data){
        if(userRepository.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        }
        else{
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User user = new User(data.email(), encryptedPassword, data.roles());
            this.userRepository.save(user);
        }
        return  ResponseEntity.ok().build();
    }

}
