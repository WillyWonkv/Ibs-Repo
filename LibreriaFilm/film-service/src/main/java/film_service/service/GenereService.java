package film_service.service;

import film_service.dto.GenereDto;
import film_service.entity.Genere;
import film_service.repository.GenereRepository;
import film_service.mapperDto.GenereMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenereService {

    private final GenereRepository genereRepository;

    public List<GenereDto>  getAllGeneri() {

        List<Genere> genere = genereRepository.findAll();
        if (genere.isEmpty()) {throw new RuntimeException("not found");}
        return genereRepository.findAll().stream()
                .map(GenereMapperDto::genereToGenereDto).toList();

    }

    public GenereDto getGenereById(long id){

        return genereRepository.findById(id)
                .map(GenereMapperDto::genereToGenereDto).orElseThrow(() -> new RuntimeException("not found"));

    }

    public GenereDto addGenere(GenereDto genereDto) {

        genereRepository.save(GenereMapperDto.newGenere(genereDto));

        return genereDto;

    }

    public GenereDto updateGenere(long id, GenereDto genereDto) {

        return genereRepository.findById(id)
                .map(genere -> {
                    genereRepository.save(GenereMapperDto.newGenere(genereDto));
                    return GenereMapperDto.genereToGenereDto(genere);
                }
        ).orElseThrow(() -> new RuntimeException("not found"));

    }

    public GenereDto deleteGenere(long id) {

        return genereRepository.findById(id).map(genere -> {

                    genere.getFilm().forEach(f -> f.getGeneri().remove(genere));
                    genere.getFilm().clear();
                    genereRepository.delete(genere);
                    return GenereMapperDto.genereToGenereDto(genere);

                })
                .orElseThrow(() -> new RuntimeException("not found"));
    }



}
