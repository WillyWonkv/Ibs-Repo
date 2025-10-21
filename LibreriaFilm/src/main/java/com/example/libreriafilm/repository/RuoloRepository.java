package com.example.libreriafilm.repository;

import com.example.libreriafilm.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo ,Long> {
    Optional<Ruolo> findByNome(String s);
}
