package com.LeaveSystem.Controller;

import com.LeaveSystem.DTO.User.LoginRequestDTO;
import com.LeaveSystem.DTO.User.LoginResponseDTO;
import com.LeaveSystem.DTO.User.RegisterRequestDTO;
import com.LeaveSystem.Domain.User.User;
import com.LeaveSystem.Infra.TokenService;
import com.LeaveSystem.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthorizationController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO data) {
        try {
            var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(emailPassword);
            var token = this.tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO("Login Sucessfull",token));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body("Error in the login");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO data) {
        if (this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest()
                .body("Email already exists");
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.userName(), data.email(), encryptedPassword, data.role());
        this.repository.save(user);
        return ResponseEntity.ok("Registration Sucessfull");
    }

}
