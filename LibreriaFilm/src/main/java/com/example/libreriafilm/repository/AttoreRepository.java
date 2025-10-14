package com.example.libreriafilm.repository;

import com.example.libreriafilm.entity.Attore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttoreRepository extends JpaRepository<Attore,Long> {
    Optional<Attore> findByNome(String nome);
}
