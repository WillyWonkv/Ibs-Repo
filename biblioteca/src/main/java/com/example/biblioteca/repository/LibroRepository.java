package com.example.biblioteca.repository;

import com.example.biblioteca.dto.LibroDto;
import com.example.biblioteca.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Libro findBytitolo(String titolo);

    @Query("from Libro l where l.titolo like %?1% or l.autore like %?1% or l.categoria.nome like %?1%")
    List<Libro> findByAllLike(String titolo);

    @Query("select new com.example.biblioteca.dto.LibroDto(l.id,l.titolo,l.autore,l.isbn,l.annoPubblicazione,l.isPrestito,l.categoria) from Libro l")
    List<LibroDto> findAllSelect();

    @Query("select new com.example.biblioteca.dto.LibroDto(l.id,l.titolo,l.autore,l.isbn,l.annoPubblicazione,l.isPrestito,l.categoria) from Libro l where l.isPrestito is true")
    List<LibroDto> findLibriInPrestito();

    @Query("select count(l.id) as conteggio, c.nome from Libro l right join l.categoria c group by c.nome order by conteggio desc")
    List<Object[]> findLibriPerCatergoria();

}
