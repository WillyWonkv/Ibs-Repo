package com.example.biblioteca.repository;

import com.example.biblioteca.dto.LibroDto;
import com.example.biblioteca.dto.UtenteDto;
import com.example.biblioteca.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

    @Query("select new com.example.biblioteca.dto.UtenteDto(u.id,u.nome,u.cognome,u.email,u.password,u.dataRegistrazione) from Utente u")
    List<UtenteDto> findAllSelect();

    @Query("from Utente u join u.prestito p where p.dataRestituzione is null and u.id = ?1")
    Utente findUtenteInPresito(long id);

    @Query("select count(p.id) as Prestiti, u.id, u.nome, u.cognome from Prestito p right join p.utente u group by u.id,u.nome,u.cognome order by Prestiti desc")
    List<Object[]> findCountPrestitoUtenti();

}
