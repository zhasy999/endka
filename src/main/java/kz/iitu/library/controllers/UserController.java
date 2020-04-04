package kz.iitu.library.controllers;

import kz.iitu.library.models.Author;
import kz.iitu.library.models.Book;
import kz.iitu.library.models.User;
import kz.iitu.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public void addUser(User user){
        if(userService.addUser(user)) {
            System.out.println("Пользователь "+user+" добавлен");
            return;
        }
        System.out.println(user + " Уже есть");
    }

    public void addAuthor(Author author){
        if(userService.addAuthor(author)) {
            System.out.println(author + "Автор добавлен");
            return;
        }
        System.out.println(author + " Уже существует");
    }

    public void editUser(User user){
        if(userService.findUserByName(user.getName()) == null) {
            System.out.println("Нету такого юзера "+user);
            return;
        }
        userService.saveUser(user);
        System.out.println(user +" изменен");

    }

    public void addBook(Long userId, Long bookId){
        userService.addBook(userId, bookId);
    }

    public Author findAuthorByName(String name){
        return userService.findAuthorByName(name);
    }

    public User findUserByName(String name){
        return userService.findUserByName(name);
    }

    public void clear() {
        userService.clear();
    }
}
