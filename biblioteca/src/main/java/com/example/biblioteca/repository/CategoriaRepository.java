package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findById(long id);

    @Query("from Categoria c where c.nome = ?1")
    Categoria findBynome(String nome);

}
