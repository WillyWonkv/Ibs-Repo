package com.example.LoginProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {

    private Long id;
    private String coverSrc;
    private String title;
    private String description;
    private int duration;
    private Set<Long> genresIds;

}
