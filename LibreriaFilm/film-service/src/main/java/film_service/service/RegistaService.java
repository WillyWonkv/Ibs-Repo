package film_service.service;

import film_service.dto.RegistaDto;
import film_service.entity.Film;
import film_service.entity.Regista;
import film_service.repository.RegistaRepository;
import film_service.mapperDto.RegistaMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistaService{

    private final RegistaRepository registaRepository;

    public List<RegistaDto> getAllRegisti(){

        List<Regista> registi = registaRepository.findAll();
        if(registi.isEmpty()){throw new RuntimeException("not found");}
        return registaRepository.findAll().stream()
                .map(RegistaMapperDto::registaToRegistaDto).toList();

    }

    public RegistaDto getRegistiById(Long id){

        return registaRepository.findById(id)
                .map(RegistaMapperDto::registaToRegistaDto)
                .orElseThrow(() -> new RuntimeException("not found"));

    }

    public RegistaDto addRegista(RegistaDto registaDto){

        registaRepository.save(RegistaMapperDto.newRegista(registaDto));
        return registaDto;

    }

    public RegistaDto updateRegista(RegistaDto registaDto, Long id) {

        return registaRepository.findById(id)
                .map(regista -> {
                            registaRepository.save(RegistaMapperDto.newRegista(registaDto));
                            return RegistaMapperDto.registaToRegistaDto(regista);
                        }
                ).orElseThrow(() -> new RuntimeException("not found"));

    }

    public RegistaDto deleteRegista(Long id) {

        Regista regista = registaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        for(Film f : regista.getFilm()){f.setRegista(null);}

        registaRepository.delete(regista);

        return RegistaMapperDto.registaToRegistaDto(regista);

    }


}
