package kz.iitu.library.controllers;

import kz.iitu.library.models.Author;
import kz.iitu.library.models.Book;
import kz.iitu.library.models.User;
import kz.iitu.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/author={authorname}")
    public Author findAuthorByName(@PathVariable String authorname){
        return userService.findAuthorByName(authorname);
    }

    @GetMapping("/user={username}")
    public User findUserByName(@PathVariable String username){
        return userService.findUserByName(username);
    }

    @PostMapping
    public String addUser(@RequestBody User user){
        if(userService.addUser(user)) {
            return ("Пользователь "+user+" добавлен");
        }
        return (user + " Уже есть");
    }

    @PostMapping("/author")
    public String addAuthor(@RequestBody Author author){
        if(userService.addAuthor(author)) {
            return(author + "Автор добавлен");

        }
        return (author + " Уже существует");
    }

    @PostMapping("/{userid}/edit")
    public String editUser(@PathVariable Long userid,@RequestBody User user){
        if(userService.findUserById(userid) == null) {
            return ("Нету такого юзера "+user);
        }
        userService.saveUser(user);
        return (user +" изменен");
    }

    @PostMapping("/addBook")
    public boolean addBook(@RequestParam Long userId,@RequestParam Long bookId){
        return userService.addBook(userId, bookId);
    }

    @DeleteMapping("/deleteUser/{username}")
    public boolean deleteUserByName(@PathVariable String username) {
        if (userService.deleteUserByName(username) > 0) {
            return true;
        }
        return false;
    }
    @DeleteMapping("/deleteBook/{bookname}")
    public boolean deleteAuthorByName(@PathVariable String authorname) {
        if (userService.deleteAuthorByName(authorname) > 0) {
            return true;
        }
        return false;
    }
    @DeleteMapping("cleanAuthorsAndUsers")
    public void clear() {
        userService.clear();
    }
}
