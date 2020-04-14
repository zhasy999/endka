package kz.iitu.library.services.interfaces;

import kz.iitu.library.models.Author;
import kz.iitu.library.models.Book;
import kz.iitu.library.models.Genre;
import kz.iitu.library.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Service
public interface BookServiceInt {
    boolean addBook(Book book);

    boolean addBookToUser(Long userId, Long bookId);

    boolean returnBookFromUser(Long userId, Long bookId);

    List<Book> findAllByStatus(Status status);

    Book findBookByName(String title);

    Book findBookById(Long id);

    List<Book> findAllByAuthor(Author author);

    List<Book> findAll();

    void save(Book book);

    void clear();

    Long deleteBookByName(String title);
}