package com.example.biblioteca.controller;

import com.example.biblioteca.dto.LibroDto;
import com.example.biblioteca.entity.Libro;
import com.example.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libri")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public List<LibroDto> showLibri() {

        return libroRepository.findAllSelect();

    }

    @GetMapping("/search/{ricerca}")
    public List<Libro> searchLibri(@PathVariable String ricerca) {

        return libroRepository.findByAllLike(ricerca);

    }

    @PostMapping("/addLibro")
    public String addLibro(@RequestBody Libro libro) {

        libroRepository.save(libro);

        return "Libro aggiunto!!";

    }

    @PutMapping("/modifyLibro")
    public String updateLibro(@RequestBody Libro libro) {

        Optional<Libro> optionalLibro = libroRepository.findById(libro.getId());

        if(optionalLibro.isPresent()) {

            Libro libroTrovato = optionalLibro.get();

            if(!libroTrovato.isPrestito()) {

                libroTrovato.setTitolo(libro.getTitolo());
                libroTrovato.setId(libro.getId());
                libroTrovato.setAnnoPubblicazione(libro.getAnnoPubblicazione());
                libroTrovato.setIsbn(libro.getIsbn());
                libroTrovato.setCategoria(libro.getCategoria());
                libroTrovato.setAutore(libro.getAutore());
                libroRepository.save(libroTrovato);
                return "Libro modificato!!";

            }else{

                return "Il libro è in prestito";

            }

        }

        return "Libro da modificare non trovato!!";

    }

    @DeleteMapping("/deleteLibro/{id}")
    public String deleteLibro(@PathVariable String id) {

        Optional libroOtional = libroRepository.findById(Long.parseLong(id));

        if(libroOtional.isPresent()) {

            Libro libro = (Libro) libroOtional.get();

            if(!libro.isPrestito()) {
                libroRepository.delete(libro);
                return "Libro eliminato!!";
            }else{
                return "Il libro è in prestito!!";
            }

        }

        return "Libro non trovato!!";

    }

    @GetMapping("/prestito")
    public List<LibroDto> findLibriInPrestito(){

        return libroRepository.findLibriInPrestito();

    }

    @GetMapping("/categoria")
    public List<Object[]> findLibriPerCategoria(){

        return libroRepository.findLibriPerCatergoria();

    }

}
