package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.entity.Film;
import com.example.libreriafilm.entity.Regista;
import com.example.libreriafilm.repository.FilmRepository;
import com.example.libreriafilm.repository.RegistaRepository;
import com.example.libreriafilm.MapperDto.RegistaMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistaService{

    private final RegistaRepository registaRepository;
    private final FilmRepository filmRepository;

    public List<RegistaDto> getAllRegisti(){

        List<Regista> registi = registaRepository.findAll();
        if(registi.isEmpty()){throw new RuntimeException("Registi not found");}
        return registaRepository.findAll().stream().map(RegistaMapperDto::registaToRegistaDto).toList();

    }

    public ResponseEntity<RegistaDto> getRegistiById(Long id){

        if(registaRepository.findById(id).isEmpty()){return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(registaRepository.findById(id)
                .map(RegistaMapperDto::registaToRegistaDto).orElse(null));

    }

    public ResponseEntity<RegistaDto> addRegista(RegistaDto registaDto){

        registaRepository.save(RegistaMapperDto.newRegista(registaDto));
        return ResponseEntity.ok().body(registaDto);

    }

    public ResponseEntity<Object> updateRegista(RegistaDto registaDto, Long id) {

        return registaRepository.findById(id)
                .map(genere -> {
                            registaRepository.save(RegistaMapperDto.newRegista(registaDto));
                            return ResponseEntity.ok().build();
                        }
                ).orElseGet(() -> ResponseEntity.notFound().build());

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
