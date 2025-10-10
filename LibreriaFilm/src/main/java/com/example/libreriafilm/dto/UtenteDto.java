package com.example.libreriafilm.dto;

import com.example.libreriafilm.entity.Prestito;

import java.util.Date;
import java.util.List;

public record UtenteDto(long id, String nome, String cognome, String email, String password, double soldi, Date dataRegistazione, List<PrestitoDto> prestito) {
}
