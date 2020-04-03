package kz.iitu.library.repo;

import kz.iitu.library.models.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findAuthorByName(String name);
}
