package com.example.LoginProject.security.service;

import com.example.LoginProject.entity.Permission;
import com.example.LoginProject.entity.Role;
import com.example.LoginProject.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Getter
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    //crea la mappa con tutti i ruoli e permessi
    //e poi crea il token con la funzione createToken
    public String generateToken(User user) {

        Map<String,Object> claims = new HashMap<>();

        claims.put("role", user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));

        claims.put("perimissions", user.getRoles().stream()
                .map(Role::getPermissions)
                .flatMap(Set::stream)
                .map(Permission::getName)
                .collect(Collectors.toSet()));

        return createToken(claims, user.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(key);
    }

    //estrazione e validazione dati dal token (da usare in altri microservizi)

    public String extractUsername(String authToken) {
        return extractClaim(authToken, Claims::getSubject);
    }

    public Date extractExpiration(String authToken) {
        return extractClaim(authToken, Claims::getExpiration);
    }

    private <T> T extractClaim(String authToken, Function<Claims, T> claimsResolver) {

        //estraggo tutti i claims tramite la chiave segreta
        final Claims claims =
                Jwts.parserBuilder()
                        .setSigningKey(getSignKey())
                        .build()
                        .parseClaimsJws(authToken).getBody();

        return claimsResolver.apply(claims);
    }

    private Boolean isTokenExpired(String authToken) {
        return extractExpiration(authToken).before(new Date());
    }

    //valido il token
    public boolean validateToken(String authToken, UserDetails userDetails) {
        final String username = extractUsername(authToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(authToken));
    }

}
