package film_service.Specification;

import film_service.entity.Genere;
import org.springframework.data.jpa.domain.Specification;

public class GenereSpecification {

    public static Specification<Genere> hasName(String nome) {
        return ((root, query, builder) ->
                builder.like(root.get("nome"), "%" + nome + "%")
                );
    }

}
