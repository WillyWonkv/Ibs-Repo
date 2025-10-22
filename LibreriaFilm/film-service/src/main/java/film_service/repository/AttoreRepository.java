package film_service.repository;

import film_service.entity.Attore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttoreRepository extends JpaRepository<Attore,Long> {
    Optional<Attore> findByNome(String nome);
}
