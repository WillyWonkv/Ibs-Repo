package com.example.libreriafilm.service;

import com.example.libreriafilm.repository.GenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenereService {

    @Autowired
    private GenereRepository genereRepository;

}
