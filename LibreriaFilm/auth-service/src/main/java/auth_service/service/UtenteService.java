package auth_service.service;

import auth_service.dto.UtenteDto;
import auth_service.entity.Ruolo;
import auth_service.repository.RuoloRepository;
import auth_service.security.jwt.JwtService;
import auth_service.security.request.AuthRequest;
import auth_service.security.request.AuthResponse;
import auth_service.entity.Utente;
import auth_service.repository.UtenteRepository;
import auth_service.mapperDto.UtenteMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final RuoloRepository ruoloRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public List<UtenteDto> getAllUtente(){

        List<Utente> utenti = utenteRepository.findAll();
        if(utenti.isEmpty()){throw new RuntimeException("No Users");}
        return utenteRepository.findAll().stream()
                .map(UtenteMapperDto::utenteToUtenteDto).toList();
    }

    public UtenteDto getUtenteById(long id) {
        return utenteRepository.findById(id)
                .map(UtenteMapperDto::utenteToUtenteDto)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    /*public UtenteDto deleteUtente(long id){

        return utenteRepository.findById(id).map(utente -> {

                    if(utente.getPrestito().stream().anyMatch(prestito -> prestito.getDataRestituzione() == null)){
                        throw new RuntimeException("Bad Request");
                    }

                    prestitoRepository.deleteAll(utente.getPrestito());

                    utenteRepository.delete(utente);
                    return UtenteMapperDto.utenteToUtenteDto(utente);

                })
                .orElseThrow(() -> new RuntimeException("not found"));

    }*/

    public AuthResponse registerUtente(Utente utente){

        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        utente.setDataRegistrazione(Date.valueOf(LocalDate.now()));

        Ruolo ruolo = ruoloRepository.findByNome("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        utente.getRuolo().add(ruolo);

        utenteRepository.save(utente);

        var JwtToken = jwtService.generateToken(utente);

        return AuthResponse.builder().token(JwtToken).build();

    }

    public AuthResponse loginUtente(AuthRequest utente){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        utente.getEmail(),
                        utente.getPassword()
                )
        );

        var user = utenteRepository.findByEmail(utente.getEmail()).orElseThrow();
        return AuthResponse.builder().token(jwtService.generateToken(user)).build();

    }

}
