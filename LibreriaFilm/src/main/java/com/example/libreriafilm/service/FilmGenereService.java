package com.example.libreriafilm.service;

import com.example.libreriafilm.repository.FilmGenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmGenereService {

    @Autowired
    private FilmGenereRepository filmGenereRepository;

}
