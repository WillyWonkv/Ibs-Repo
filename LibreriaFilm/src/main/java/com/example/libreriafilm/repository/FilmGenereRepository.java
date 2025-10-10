package com.example.libreriafilm.repository;

import com.example.libreriafilm.entity.FilmGenere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmGenereRepository extends JpaRepository<FilmGenere, Long> {
}
