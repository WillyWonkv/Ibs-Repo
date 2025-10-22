package auth_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UtenteDto(long id, String nome, String cognome, String email, String password, double soldi, Date dataRegistazione) {
}
