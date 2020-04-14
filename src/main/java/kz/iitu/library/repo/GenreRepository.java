package kz.iitu.library.repo;

import kz.iitu.library.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findGenreByNameIgnoreCase(String name);
    Long deleteGenreByNameIgnoreCase(String name);
}
