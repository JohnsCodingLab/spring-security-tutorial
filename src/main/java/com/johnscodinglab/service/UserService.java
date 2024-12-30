package com.johnscodinglab.service;

import com.johnscodinglab.config.PasswordEncoderConfig;
import com.johnscodinglab.model.Users;
import com.johnscodinglab.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final PasswordEncoderConfig passwordEncoderConfig;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public Users registerUser(Users user) {
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String verify(Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        return "Failure";
    }
}
