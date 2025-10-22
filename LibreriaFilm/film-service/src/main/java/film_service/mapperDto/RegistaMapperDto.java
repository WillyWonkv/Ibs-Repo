package film_service.mapperDto;

import film_service.dto.AttoreDto;
import film_service.dto.FilmDto;
import film_service.dto.GenereDto;
import film_service.dto.RegistaDto;
import film_service.entity.Regista;

public class RegistaMapperDto {

    public static RegistaDto registaToRegistaDto(Regista r) {

        return new RegistaDto(
                r.getId(),
                r.getNome(),
                r.getDataNascita(),
                r.getFilm().stream()
                        .map(f -> new FilmDto(
                                f.getId(),
                                f.getTitolo(),
                                f.getDescrizione(),
                                f.getPrezzo(),
                                f.getAnnoUscita(),
                                f.getDurata(),
                                null,
                                f.getAttori().stream()
                                        .map(a -> new AttoreDto(
                                                a.getId(),
                                                a.getNome(),
                                                a.getDataNascita(),
                                                null
                                        )).toList(),
                                f.getGeneri().stream()
                                        .map(g -> new GenereDto(
                                                g.getId(),
                                                g.getNome(),
                                                null
                                        )).toList()
                        )).toList()
        );

    }

    public static Regista newRegista(RegistaDto registaDto) {

        Regista regista = new Regista();
        regista.setNome(registaDto.nome());
        regista.setDataNascita(registaDto.dataNascita());
        return  regista;

    }

}
