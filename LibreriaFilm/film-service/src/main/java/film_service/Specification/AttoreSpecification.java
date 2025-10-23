package film_service.Specification;

import film_service.entity.Attore;
import org.springframework.data.jpa.domain.Specification;

public class AttoreSpecification {

    public static Specification<Attore> hasName(String nome) {
        return ((root, query, builder) ->
                builder.like(root.get("nome"), "%" + nome + "%")
                );
    }

}
