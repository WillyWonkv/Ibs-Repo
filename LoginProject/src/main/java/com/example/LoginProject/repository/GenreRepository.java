package com.example.LoginProject.repository;

import com.example.LoginProject.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GenreRepository extends JpaRepository<Genre,Long>, JpaSpecificationExecutor<Genre> {
}
