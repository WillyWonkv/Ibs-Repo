package com.example.biblioteca.repository;

import com.example.biblioteca.dto.PrestitoLibroDto;
import com.example.biblioteca.dto.PrestitoUtenteDto;
import com.example.biblioteca.entity.Prestito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrestitoRepository extends JpaRepository<Prestito, Long> {

    @Query("from Prestito p join p.libro l join p.utente u where l.id = ?2 and u.id = ?1 and p.dataRestituzione is null")
    Prestito findPrestito(long idUtente, long idLibro);

    @Query("select new com.example.biblioteca.dto.PrestitoLibroDto(p.id,p.dataPrestito,p.dataRestituzione, new com.example.biblioteca.dto.UtenteDto(u.id, u.nome, u.cognome,u.email,u.password,u.dataRegistrazione)) from Prestito p join p.libro l join p.utente u where l.id = ?1")
    List<PrestitoLibroDto> findByLibroSelect(Long id);

    @Query("select new com.example.biblioteca.dto.PrestitoUtenteDto(p.id,p.dataPrestito,p.dataRestituzione, new com.example.biblioteca.dto.LibroDto(l.id,l.titolo,l.autore,l.isbn,l.annoPubblicazione,l.isPrestito,l.categoria)) from Prestito p join p.utente u join p.libro l where u.id = ?1")
    List<PrestitoUtenteDto> findByUtenteSelect(Long id);


}
