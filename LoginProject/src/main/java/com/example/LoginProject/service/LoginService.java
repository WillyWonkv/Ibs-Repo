package com.example.LoginProject.service;

import com.example.LoginProject.entity.Role;
import com.example.LoginProject.entity.User;
import com.example.LoginProject.repository.RoleRepository;
import com.example.LoginProject.repository.UserRepository;
import com.example.LoginProject.security.service.JwtService;
import com.example.LoginProject.security.request.AuthRequest;
import com.example.LoginProject.security.request.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registerUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role Role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(Role);

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder().token(jwtToken).build();

    }

    public AuthResponse loginUser(AuthRequest user) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        var userDb = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return AuthResponse.builder().token(jwtService.generateToken(userDb)).build();

    }

}
