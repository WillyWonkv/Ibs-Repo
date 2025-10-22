package film_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record GenereDto(long id, String nome, List<FilmDto> film) {
}
