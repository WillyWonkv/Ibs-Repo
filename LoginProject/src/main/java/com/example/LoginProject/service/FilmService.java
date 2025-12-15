package com.example.LoginProject.service;

import com.example.LoginProject.Specification.FilmSpecification;
import com.example.LoginProject.dto.FilmDTO;
import com.example.LoginProject.entity.Film;
import com.example.LoginProject.exception.EmptyListException;
import com.example.LoginProject.repository.FilmRepository;
import com.example.LoginProject.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmService {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    @Value("${film-project.cover-path}")
    private String coverPath;

    public List<Film> findAllFilms() {
        try{
            List<Film> films = filmRepository.findAll();
            if(films.isEmpty()){
                log.warn("No films found");
                throw new EmptyListException("Film List is Empty");
            }
            log.info("Films found");
            return films;
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    public List<Film> findFilmsByPage(int page, int size) {

        try{
            Pageable pageable = PageRequest.of(
                    page,
                    size,
                    Sort.by(Sort.Direction.ASC, "id")
                    );
            Page<Film> films = filmRepository.findAll(pageable);
            if(films.isEmpty()){
                log.warn("No films found");
            }
            log.info("Films found");
            return films.getContent();
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
    public Film saveFilm(FilmDTO filmDTO, InputStream file) throws IOException {
        try{

            Film film = new Film();
            film.setTitle(filmDTO.getTitle());
            film.setDescription(filmDTO.getDescription());
            film.setDuration(filmDTO.getDuration());
            film.setGenres(filmDTO.getGenres().stream()
                    .map(g -> genreRepository.findById(g.getId())
                            .orElseThrow(() -> new RuntimeException("Genre not found")))
                    .collect(Collectors.toSet()));

            String filename = saveCoverFile(file);
            film.setCoverSrc(filename);

            Film saved = filmRepository.save(film);
            log.info("Film saved successfully with id {}", saved.getId());
            return saved;
        }catch (Exception e){
            log.error("Error saving film", e);
            throw e;
        }
    }

    @Transactional
    public Film updateFilm(FilmDTO filmDTO, InputStream file) throws IOException {
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

            if(file != null){

                try {
                    Files.deleteIfExists(Paths.get(coverPath, filmDTO.getCoverSrc()));
                } catch (IOException e) {
                    log.warn("Can't delete file, cover not found", e);
                }

                String filename = saveCoverFile(file);
                film.setCoverSrc(filename);
            }

            log.info("Film modified successfully with id {}", filmDTO.getId());
            return filmRepository.save(film);

        }catch (Exception e){
            log.error("Error updating film", e);
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

            try {
                Files.deleteIfExists(Paths.get(coverPath, film.getCoverSrc()));
            } catch (IOException e) {
                log.warn("cover not found", e);
            }

            filmRepository.deleteById(id);
            log.info("Film deleted successfully with id {}", id);

        }catch (Exception e){
            log.error("Error deleting film", e);
            throw e;
        }
    }


    public List<Film> findFilmByGenre(long genreId){
        try{
            List<Film> filmsFiltered = filmRepository.findAll(FilmSpecification.findFilmByGenre(genreId));
            if(filmsFiltered.isEmpty()){
                log.warn("No films found for genre {}", genreId);
                throw new EmptyListException("Film List is Empty");
            }
            log.info("Films found successfully with id {}", genreId);
            return filmsFiltered;
        }catch (Exception e){
            log.error("Error getting films by genre", e);
            throw e;
        }
    }

    public List<Film> findFilmByTitle(String title){
        try {
            List<Film> filmsFiltered = filmRepository.findAll(FilmSpecification.findFilmByTitle(title));
            if(filmsFiltered.isEmpty()){
                log.warn("No films found for title {}", title);
                throw new EmptyListException("Film List is Empty");
            }
            log.info("{} Films found successfully with title {}",filmsFiltered.size(), title);
            return filmsFiltered;
        }catch (Exception e){
            log.error("Error getting films by title", e);
            throw e;
        }
    }

    public Resource getFilmCover(String filename) throws IOException {

        Path path = Paths.get(coverPath).resolve(filename);

        if (!filename.toLowerCase().endsWith(".jpg") && !filename.toLowerCase().endsWith(".jpeg")) {
            throw new IllegalArgumentException("Only JPG/JPEG files are allowed");
        }

        if(!Files.exists(path)){
            throw new FileNotFoundException("Cover Not Found");
        }

        return new UrlResource(path.toUri());
    }

    private String saveCoverFile(InputStream file) throws IOException {
        if(file == null) return null;

        Path upPath = Paths.get(coverPath);

        if(!Files.exists(upPath)){
            Files.createDirectories(upPath);
        }

        String filename = UUID.randomUUID() + ".jpg";
        Path filePath = upPath.resolve(filename);

        Files.copy(file, filePath, StandardCopyOption.REPLACE_EXISTING);

        return filename;

    }

}
