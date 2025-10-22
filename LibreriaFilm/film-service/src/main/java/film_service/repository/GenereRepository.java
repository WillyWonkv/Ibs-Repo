package film_service.repository;

import film_service.entity.Genere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenereRepository extends JpaRepository<Genere, Long> {
    Optional<Genere> findByNome(String nome);
}
