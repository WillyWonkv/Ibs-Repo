package com.example.libreriafilm.service;

import com.example.libreriafilm.repository.AttoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttoreService {

    @Autowired
    private AttoreRepository attoreRepository;

}
