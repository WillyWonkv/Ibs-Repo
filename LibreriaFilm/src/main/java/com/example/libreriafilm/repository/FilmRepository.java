package com.example.libreriafilm.repository;

import com.example.libreriafilm.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {

}
