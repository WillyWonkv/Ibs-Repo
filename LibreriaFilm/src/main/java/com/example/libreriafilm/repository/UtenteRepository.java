package com.example.libreriafilm.repository;

import com.example.libreriafilm.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente,Long> {
}
