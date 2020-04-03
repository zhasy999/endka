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
            System.out.println("Пользователь добавлен");
            return;
        }
        System.out.println("Error");
    }

    public void addAuthor(Author author){
        if(userService.addAuthor(author)) {
            System.out.println("Автор добавлен");
            return;
        }
        System.out.println("Error");
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
