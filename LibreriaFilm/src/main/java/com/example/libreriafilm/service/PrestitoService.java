package com.example.libreriafilm.service;

import com.example.libreriafilm.repository.PrestitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestitoService {

    @Autowired
    private PrestitoRepository prestitoRepository;

}
