package com.example.libreriafilm.security.user;

import com.example.libreriafilm.entity.Utente;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class CustomUserDetails implements UserDetails {

    private final Utente utente;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        utente.getRuolo().forEach(role -> {

            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNome().toUpperCase()));

            role.getPermesso().forEach(permesso ->
                    authorities.add(new SimpleGrantedAuthority(permesso.getNome())));

        });

        return authorities;

    }

    @Override
    public String getPassword() {
        return utente.getPassword();
    }

    @Override
    public String getUsername() {
        return utente.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
