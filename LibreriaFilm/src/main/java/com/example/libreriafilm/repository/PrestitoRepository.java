package com.example.libreriafilm.repository;

import com.example.libreriafilm.entity.Prestito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestitoRepository extends JpaRepository<Prestito, Long> {
}
