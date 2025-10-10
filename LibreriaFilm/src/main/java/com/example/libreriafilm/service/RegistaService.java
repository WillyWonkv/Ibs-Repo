package com.example.libreriafilm.service;

import com.example.libreriafilm.repository.RegistaRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistaService{

    @Autowired
    private RegistaRepository registaRepository;

}
