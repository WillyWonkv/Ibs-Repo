package com.example.LoginProject.Specification;

import com.example.LoginProject.entity.Film;
import org.springframework.data.jpa.domain.Specification;

public class FilmSpecification {

    public static Specification<Film> findFilmByGenre(long genreId) {
        return ((root, query, builder) ->
                builder.equal(root.get("genres").get("id"), genreId));
    }

    public static Specification<Film> findFilmByTitle(String title) {
        return ((root, query, builder) ->
                builder.like(root.get("title").as(String.class), "%" + title + "%"));
    }

}
