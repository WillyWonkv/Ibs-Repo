package com.example.libreriafilm.MapperDto;

import com.example.libreriafilm.dto.UtenteDto;
import com.example.libreriafilm.entity.Utente;

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

    public static Utente newUtente(UtenteDto utenteDto){

        Utente utente = new Utente();
        utente.setNome(utenteDto.nome());
        utente.setCognome(utenteDto.cognome());
        utente.setEmail(utenteDto.email());
        utente.setPassword(utenteDto.password());
        utente.setSoldi(utenteDto.soldi());
        utente.setDataRegistrazione(Date.valueOf(LocalDate.now()));
        return utente;

    }

}
