package auth_service.mapperDto;

import auth_service.dto.UtenteDto;
import auth_service.entity.Utente;

public class UtenteMapperDto {

    public static UtenteDto utenteToUtenteDto(Utente u){

        return new UtenteDto(
                u.getId(),
                u.getNome(),
                u.getCognome(),
                u.getEmail(),
                u.getPassword(),
                u.getSoldi(),
                u.getDataRegistrazione()
        );

    }


}
