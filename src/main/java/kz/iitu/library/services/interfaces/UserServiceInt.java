package kz.iitu.library.services.interfaces;

import kz.iitu.library.models.Author;
import kz.iitu.library.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInt {
    boolean addUser(User user);

    User findUserById(Long id);

    Author findAuthorById(Long id);

    void saveUser(User user);

    void clear();
}
