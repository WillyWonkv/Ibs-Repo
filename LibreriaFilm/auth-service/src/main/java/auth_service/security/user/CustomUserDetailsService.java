package auth_service.security.user;

import auth_service.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UtenteRepository utenteRepository;

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> utenteRepository.findByEmail(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

}
