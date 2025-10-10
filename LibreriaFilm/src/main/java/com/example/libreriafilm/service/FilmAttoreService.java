package com.example.libreriafilm.service;

import com.example.libreriafilm.repository.FilmAttoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmAttoreService {

    @Autowired
    private FilmAttoreRepository filmAttoreRepository;

}
