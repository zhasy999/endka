package kz.iitu.library.services.interfaces;

import kz.iitu.library.models.Author;
import kz.iitu.library.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceInt {
    boolean addUser(User user);

    boolean addAuthor(Author author);

    User findUserById(Long id);

    Author findAuthorById(Long id);

    User findUserByName(String name);

    Author findAuthorByName(String name);

    Long deleteUserByName(String name);

    Long deleteAuthorByName(String name);

    void saveUser(User user);

    boolean addBook(Long userId, Long bookId);

    List<User> findAllUsers();

    void clear();
}
