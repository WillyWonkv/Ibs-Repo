package film_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AttoreDto(long id, String nome, Date dataNascita, List<FilmDto> film) {
}
