package auth_service.repository;

import auth_service.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo ,Long> {
    Optional<Ruolo> findByNome(String s);
}
