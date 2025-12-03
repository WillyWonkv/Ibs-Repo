package com.example.LoginProject.service;

import com.example.LoginProject.dto.FilmDTO;
import com.example.LoginProject.entity.Film;
import com.example.LoginProject.entity.Genre;
import com.example.LoginProject.exception.EmptyListException;
import com.example.LoginProject.repository.FilmRepository;
import com.example.LoginProject.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmService {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    public List<Film> findAllFilms() {
        try{
            List<Film> films = filmRepository.findAll();
            if(films.isEmpty()){
                log.warn("No films found");
                throw new EmptyListException("Film List is Empty");
            }
            return films;
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    public Film findFilmById(Long id){
        try{
            return filmRepository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("No Film found with id {}", id);
                        return new EmptyListException("Film Not Found");
                    });
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional
    public Film saveFilm(FilmDTO filmDTO){
        try{

            Film film = new Film();
            film.setCoverSrc(filmDTO.getCoverSrc());
            film.setTitle(filmDTO.getTitle());
            film.setDescription(filmDTO.getDescription());
            film.setDuration(filmDTO.getDuration());
            film.setGenres(filmDTO.getGenres().stream()
                    .map(g -> genreRepository.findById(g.getId())
                            .orElseThrow(() -> new RuntimeException("Genre not found")))
                    .collect(Collectors.toSet()));

            Film saved = filmRepository.save(film);
            log.info("Film saved successfully with id {}", saved.getId());
            return saved;
        }catch (Exception e){
            log.error("Error saving film", e);
            throw e;
        }
    }

    @Transactional
    public void deleteFilmById(Long id){
        try{

            Film film = filmRepository.findById(id)
                    .orElseThrow(() -> {
                            log.error("Film Not Found with id {}", id);
                            return new EmptyListException("Film Not Found");
                        });

            film.getGenres().clear();
            filmRepository.deleteById(id);
            log.info("Film deleted successfully with id {}", id);

        }catch (Exception e){
            log.error("Error deleting film", e);
            throw e;
        }
    }

    @Transactional
    public Film updateFilm(FilmDTO filmDTO){
        try{

            Film film = filmRepository.findById(filmDTO.getId())
                    .orElseThrow(() -> {
                       log.error("Film Not Found with id {}", filmDTO.getId());
                       return new EntityNotFoundException("Film Not Found");
                    });

            film.setCoverSrc(filmDTO.getCoverSrc());
            film.setTitle(filmDTO.getTitle());
            film.setDescription(filmDTO.getDescription());
            film.setDuration(filmDTO.getDuration());
            film.setGenres(filmDTO.getGenres().stream()
                    .map(g -> genreRepository.findById(g.getId())
                            .orElseThrow(() -> new RuntimeException("Genre not found")))
            .collect(Collectors.toSet()));

            log.info("Film modified successfully with id {}", filmDTO.getId());
            return filmRepository.save(film);

        }catch (Exception e){
            log.error("Error updating film", e);
            throw e;
        }
    }

}
