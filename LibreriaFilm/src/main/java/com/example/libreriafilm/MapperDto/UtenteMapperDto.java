package com.example.libreriafilm.MapperDto;

import com.example.libreriafilm.dto.UtenteDto;
import com.example.libreriafilm.entity.Utente;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;

public class UtenteMapperDto {

    public static UtenteDto utenteToUtenteDto(Utente u){

        return new UtenteDto(
                u.getId(),
                u.getNome(),
                u.getCognome(),
                u.getEmail(),
                u.getPassword(),
                u.getSoldi(),
                u.getDataRegistrazione(),
                null
        );

    }


}
