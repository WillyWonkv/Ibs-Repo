package film_service.repository;

import film_service.entity.Attore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AttoreRepository extends JpaRepository<Attore,Long>, JpaSpecificationExecutor<Attore> {
    Optional<Attore> findByNome(String nome);
}
