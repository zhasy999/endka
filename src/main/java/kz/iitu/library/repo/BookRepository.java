package kz.iitu.library.repo;

import kz.iitu.library.models.Author;
import kz.iitu.library.models.Book;
import kz.iitu.library.models.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByStatus(Status status);

    Book findBookByTitleIgnoreCase(String title);

    Book findBookByAuthorsContaining(Author author);
}
