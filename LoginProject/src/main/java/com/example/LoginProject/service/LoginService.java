package com.example.LoginProject.service;

import com.example.LoginProject.entity.Role;
import com.example.LoginProject.entity.User;
import com.example.LoginProject.exception.RoleNotFoundException;
import com.example.LoginProject.exception.UsernameAlreadyExistsException;
import com.example.LoginProject.repository.RoleRepository;
import com.example.LoginProject.repository.UserRepository;
import com.example.LoginProject.security.service.JwtService;
import com.example.LoginProject.security.request.AuthRequest;
import com.example.LoginProject.security.request.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registerUser(AuthRequest userDto) {

        try {

            if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
                throw new UsernameAlreadyExistsException("Username already exists");
            }

            User user = new User();
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            Role role = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RoleNotFoundException("Role not found"));

            user.getRoles().add(role);

            userRepository.save(user);
            log.info("User {} registered successfully", user.getUsername());

            var jwtToken = jwtService.generateToken(user);
            return AuthResponse.
                    builder().
                    token(jwtToken).
                    build();


        }catch (UsernameAlreadyExistsException | RoleNotFoundException e) {
            throw e;
        } catch (Exception e){
            log.error("Error while registering user", e);
            throw e;
        }

    }

    public AuthResponse loginUser(AuthRequest user) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );

            var userDb = userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException(user.getUsername()));

            log.info("User {} logged in successfully", user.getUsername());
            return AuthResponse.builder().token(jwtService.generateToken(userDb)).build();

        }catch (UsernameNotFoundException e) {
            throw e;
        }catch (Exception e){
            log.error("Error while login user", e);
            throw e;
        }

    }

}
