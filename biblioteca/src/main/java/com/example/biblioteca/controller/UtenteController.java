package com.example.biblioteca.controller;

import com.example.biblioteca.dto.UtenteDto;
import com.example.biblioteca.entity.Utente;
import com.example.biblioteca.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping
    public List<UtenteDto> showUtenti() {
        return utenteRepository.findAllSelect();
    }

    @PostMapping("/addUtente")
    public String addUtente(@RequestBody Utente utente) {

        utenteRepository.save(utente);

        return "Utente registrato!!";

    }

    @PutMapping("/modifyUtente")
    public String updateUtente(@RequestBody Utente utente) {

        Optional<Utente> optUtente = utenteRepository.findById(utente.getId());

        if (optUtente.isPresent()) {

            Utente u = optUtente.get();
            u.setNome(utente.getNome());
            u.setCognome(utente.getCognome());
            u.setEmail(utente.getEmail());
            u.setPassword(utente.getPassword());
            u.setDataRegistrazione(utente.getDataRegistrazione());
            utenteRepository.save(u);

            return "Utente aggiornato!!";

        }

        return "Utente non trovato!!";

    }

    @DeleteMapping("/deleteUtente/{id}")
    public String deleteUtente(@PathVariable long id) {

        Optional<Utente> optUtente = utenteRepository.findById(id);

        if (optUtente.isPresent()) {

            if(utenteRepository.findUtenteInPresito(id) == null) {

                utenteRepository.deleteById(id);
                return "Utente Eliminato!!";

            }

            return "L'utente ha un libro in prestito";

        }

        return "Utente non trovato!!";

    }

    @GetMapping("/prestiti")
    public List<Object[]> showCountPrestitoUtenti() {

        return utenteRepository.findCountPrestitoUtenti();

    }

}
