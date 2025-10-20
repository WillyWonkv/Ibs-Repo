package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.entity.Attore;
import com.example.libreriafilm.entity.Film;
import com.example.libreriafilm.repository.AttoreRepository;
import com.example.libreriafilm.MapperDto.AttoreMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AttoreService {

    private final AttoreRepository attoreRepository;

    public List<AttoreDto> getAllAttori() {

        List<Attore> attori = attoreRepository.findAll();

        if(attori.isEmpty()) {throw new RuntimeException("not found");}

        return attori.stream()
                .map(AttoreMapperDto::attoreToAttoreDto).toList();

    }

    public AttoreDto getAttoreById(long id) {

        return attoreRepository.findById(id)
                .map(AttoreMapperDto::attoreToAttoreDto).orElseThrow(() -> new RuntimeException("not found"));

    }

    public AttoreDto addAttore(AttoreDto attoreDto) {

        attoreRepository.save(AttoreMapperDto.newAttore(attoreDto));
        return attoreDto;

    }

    public AttoreDto updateAttore(AttoreDto attoreDto, long id) {

        return  attoreRepository.findById(id)
                .map(attore -> {
                            attoreRepository.save(AttoreMapperDto.newAttore(attoreDto));
                            return AttoreMapperDto.attoreToAttoreDto(attore);
                        }
                ).orElseThrow(() -> new RuntimeException("not found"));

    }

    public AttoreDto deleteAttore(long id) {

        Attore attore = attoreRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));

        for(Film f : attore.getFilm()) {
            f.getAttori().remove(attore);
        }

        attore.getFilm().clear();
        attoreRepository.delete(attore);

        return AttoreMapperDto.attoreToAttoreDto(attore);

    }



}
