package film_service.Specification;

import film_service.entity.Regista;
import org.springframework.data.jpa.domain.Specification;

public class RegistaSpecification {

    public static Specification<Regista> hasName(String nome) {
        return (root, query, builder) ->
                builder.like(root.get("nome"), "%" + nome + "%");
    }

}
