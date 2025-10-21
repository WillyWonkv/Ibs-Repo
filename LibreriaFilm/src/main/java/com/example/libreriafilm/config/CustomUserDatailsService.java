package com.example.libreriafilm.config;

import com.example.libreriafilm.entity.CustomUserDetails;
import com.example.libreriafilm.entity.Utente;
import com.example.libreriafilm.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
@RequiredArgsConstructor
public class CustomUserDatailsService implements UserDetailsService {

    private final UtenteRepository utenteRepository;

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> new CustomUserDetails(utenteRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utente utente = utenteRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(utente);

    }
}
