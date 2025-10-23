package film_service.Specification;

import film_service.entity.Attore;
import film_service.entity.Film;
import film_service.entity.Genere;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class FilmSpecification {

    public static Specification<Film> hasName(String filmName) {
        return ((root, query, builder) ->
                builder.like(root.get("titolo").as(String.class), "%" + filmName + "%")
                );
    }

    public static Specification<Film> hasGenere(String genereNome) {
        return ((root, query, builder) -> {
            Join<Film, Genere> generi = root.join("generi");
            return builder.like(generi.get("nome").as(String.class), "%" + genereNome + "%");
        });
    }

    public static Specification<Film> hasAttore(String attoreNome) {
        return (((root, query, builder) -> {
            Join<Film, Attore> attori = root.join("attori");
            return builder.like(attori.get("nome").as(String.class), "%" + attoreNome + "%");
        }));
    }

    public static Specification<Film> totalSearch(String ricerca) {
        return ((root, query, builder) ->{

            if (query != null) {
                query.distinct(true);
            }

            return builder.or(
                    hasName(ricerca).toPredicate(root, query, builder),
                    hasAttore(ricerca).toPredicate(root, query, builder),
                    hasGenere(ricerca).toPredicate(root, query, builder));
        });
    }

}
