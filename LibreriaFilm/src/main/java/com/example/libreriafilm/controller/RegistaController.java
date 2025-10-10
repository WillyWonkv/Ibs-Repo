package com.example.libreriafilm.controller;

import com.example.libreriafilm.service.RegistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regista")
public class RegistaController {

    @Autowired
    private RegistaService registaService;

}
