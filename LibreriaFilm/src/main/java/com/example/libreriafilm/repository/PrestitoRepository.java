package com.example.libreriafilm.repository;

import com.example.libreriafilm.entity.Prestito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrestitoRepository extends JpaRepository<Prestito, Long> {

    @Query("from Prestito p join p.film f where f.id = ?1 and p.dataRestituzione is null")
    List<Prestito> findFilmInPrestito(long id);

}
