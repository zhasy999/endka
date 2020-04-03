package kz.iitu.library.controllers;

import kz.iitu.library.models.*;
import kz.iitu.library.services.BookService;
import kz.iitu.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    public void addBook(Book book) {
        if (bookService.addBook(book)) {
            System.out.println("Книга добавлена");
            return;
        }
        System.out.println("Error");
    }

    public void editBook(Book book) {
        if (bookService.findBookByName(book.getTitle()) != null && book.getId()>0) {
            bookService.save(book);
            System.out.println("Книга переработано");
            return;
        }
        System.out.println("Error");
    }

    public void addBookToUser(Long userId, Long bookId) {
        if (bookService.addBookToUser(userId, bookId)) {
            System.out.println("Книга добавлена пользователю " + userId);
            bookService.findBookById(bookId).notifyMe();
            return;
        }
        System.out.println("Error");
    }

    public void returnBookFromUser(Long userId, Long bookId) {
        if (bookService.returnBookFromUser(userId, bookId)) {
            System.out.println("Книга вернулась с пользователя " + userId);
            bookService.findBookById(bookId).notifyMe();
            return;
        }
        System.out.println("Error");
    }

    public void findAllByStatus(Status status){
        System.out.println(bookService.findAllByStatus(status));
    }

    public Book findBookByName(String title) {
        return bookService.findBookByName(title);
    }

    public Book findBookByAuthor(String authorname){
        return bookService.findBookByAuthor(userService.findAuthorByName(authorname));
    }

    public void clear() {
        bookService.clear();
    }
}
