package film_service.repository;

import film_service.entity.Regista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistaRepository extends JpaRepository<Regista,Long> {
    Optional<Regista> findByNome(String nome);
}
