package com.example.biblioteca.controller;

import com.example.biblioteca.dto.PrestitoLibroDto;
import com.example.biblioteca.dto.PrestitoUtenteDto;
import com.example.biblioteca.entity.Libro;
import com.example.biblioteca.entity.Prestito;
import com.example.biblioteca.entity.Utente;
import com.example.biblioteca.repository.LibroRepository;
import com.example.biblioteca.repository.PrestitoRepository;
import com.example.biblioteca.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestito")
public class PrestitoController {

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    PrestitoRepository prestitoRepository;

    @Autowired
    UtenteRepository utenteRepository;

    @PostMapping("/addPrestito/libro/{idLibro}/utente/{idUtente}")
    public String createPrestito(@PathVariable Long idLibro, @PathVariable Long idUtente) {

        Optional <Libro> libro = libroRepository.findById(idLibro);
        Optional <Utente> utente = utenteRepository.findById(idUtente);

        if (!libro.isPresent()) {
            return "Libro non trovata";
        }else if (!utente.isPresent()) {
            return "Utente non trovato";
        }

        if(libro.get().isPrestito()){

            return "Il libro è già in prestito!!";

        }

        libro.get().setPrestito(true);
        libroRepository.save(libro.get());
        Prestito prestito = new Prestito(new Date(), null, utente.get(), libro.get());
        prestitoRepository.save(prestito);

        return "Prestito salvato!!";

    }

    @PutMapping("/restituito/libro/{idLibro}/utente/{idUtente}")
    public String libroRestituito(@PathVariable Long idLibro, @PathVariable Long idUtente) {

        Optional <Libro> libro = libroRepository.findById(idLibro);
        Optional <Utente> utente = utenteRepository.findById(idUtente);

        if (!libro.isPresent()) {return "Libro non trovata";}
        else if (!utente.isPresent()) {return "Utente non trovato";}

        Prestito prestito = prestitoRepository.findPrestito(idUtente,idLibro);

        if(prestito == null){

            return "Prestito non trovato";

        }

        prestito.setDataRestituzione(new Date());
        prestitoRepository.save(prestito);
        libro.get().setPrestito(false);
        libroRepository.save(libro.get());

        return "Libro restituto!!";


    }

    @GetMapping("/cronologia/libro/{id}")
    public List<PrestitoLibroDto> findPrestitiPerLibro(@PathVariable long id) {

        Optional <Libro> optionalLibro = libroRepository.findById(id);

        if (!optionalLibro.isPresent()) {return null;}

        return prestitoRepository.findByLibroSelect(id);

    }

    @GetMapping("/cronologia/utente/{id}")
    public List<PrestitoUtenteDto> findPrestitiPerUtente(@PathVariable long id) {

        Optional <Utente> optionalUtente = utenteRepository.findById(id);

        if (!optionalUtente.isPresent()) {return null;}

        return prestitoRepository.findByUtenteSelect(id);

    }


}
