package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.entity.Film;
import com.example.libreriafilm.entity.Regista;
import com.example.libreriafilm.repository.FilmRepository;
import com.example.libreriafilm.repository.RegistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistaService{

    @Autowired
    private RegistaRepository registaRepository;
    @Autowired
    private FilmRepository filmRepository;

    public ResponseEntity<List<RegistaDto>> getAllRegisti(){

        if(registaRepository.findAll().isEmpty()){return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(registaRepository.findAll().stream()
                .map(r -> new RegistaDto(
                        r.getId(),
                        r.getNome(),
                        r.getDataNascita(),
                        r.getFilm().stream()
                                .map(f -> new FilmDto(
                                        f.getId(),
                                        f.getTitolo(),
                                        f.getDescrizione(),
                                        f.getPrezzo(),
                                        f.getAnnoUscita(),
                                        f.getDurata(),
                                        null,
                                        f.getAttori().stream()
                                                .map(a -> new AttoreDto(
                                                        a.getId(),
                                                        a.getNome(),
                                                        a.getDataNascita(),
                                                        null
                                                )).toList(),
                                        f.getGeneri().stream()
                                                .map(g -> new GenereDto(
                                                        g.getId(),
                                                        g.getNome(),
                                                        null
                                                )).toList(),
                                        null
                                )).toList()
                )).toList());

    }

    public ResponseEntity<RegistaDto> getRegistiById(Long id){

        if(registaRepository.findById(id).isEmpty()){return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(registaRepository.findById(id)
                .map(r -> new RegistaDto(
                        r.getId(),
                        r.getNome(),
                        r.getDataNascita(),
                        r.getFilm().stream()
                                .map(f -> new FilmDto(
                                        f.getId(),
                                        f.getTitolo(),
                                        f.getDescrizione(),
                                        f.getPrezzo(),
                                        f.getAnnoUscita(),
                                        f.getDurata(),
                                        null,
                                        f.getAttori().stream()
                                                .map(a -> new AttoreDto(
                                                        a.getId(),
                                                        a.getNome(),
                                                        a.getDataNascita(),
                                                        null
                                                )).toList(),
                                        f.getGeneri().stream()
                                                .map(g -> new GenereDto(
                                                        g.getId(),
                                                        g.getNome(),
                                                        null
                                                )).toList(),
                                        null
                                )).toList()
                )).orElse(null));

    }

    public ResponseEntity<RegistaDto> addRegista(RegistaDto registaDto){

        Regista regista = new Regista();

        regista.setNome(registaDto.nome());
        regista.setDataNascita(registaDto.dataNascita());

        registaRepository.save(regista);
        return ResponseEntity.ok(getRegistiById(regista.getId()).getBody());
    }

    public ResponseEntity<RegistaDto> updateRegista(RegistaDto registaDto, Long id) {

        Optional<Regista> registaOptional = registaRepository.findById(id);

        if (registaOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}

        Regista regista = registaOptional.get();

        regista.setNome(registaDto.nome());
        regista.setDataNascita(registaDto.dataNascita());
        registaRepository.save(regista);

        return ResponseEntity.ok(getRegistiById(regista.getId()).getBody());

    }

    public ResponseEntity<RegistaDto> deleteRegista(Long id) {

        Optional<Regista> registaOptional = registaRepository.findById(id);
        if (registaOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}

        Regista regista = registaOptional.get();

        for(Film f : regista.getFilm()){

            f.setRegista(null);

        }

        registaRepository.delete(regista);

        return ResponseEntity.ok().build();

    }

    public ResponseEntity<Object> setRegistaToFilm(Long idRegista, Long idFilm) {

        Optional <Regista> registaOptional = registaRepository.findById(idRegista);
        Optional <Film> filmOptional = filmRepository.findById(idFilm);

        if (registaOptional.isEmpty() || filmOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}

        Film film = filmOptional.get();
        Regista regista = registaOptional.get();

        film.setRegista(regista);
        filmRepository.save(film);

        return ResponseEntity.ok().build();

    }

}
