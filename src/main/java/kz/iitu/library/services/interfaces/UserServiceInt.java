package kz.iitu.library.services.interfaces;

import kz.iitu.library.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceInt {
    boolean addUser(User user);

    User findUserById(Long id);

    User findUserByName(String name);

    Long deleteUserByName(String name);

    void saveUser(User user);

    boolean addCar(Long userId, Long carId);

    List<User> findAllUsers();

    void clear();
}
