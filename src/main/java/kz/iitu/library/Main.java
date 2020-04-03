package kz.iitu.library;

import kz.iitu.library.controllers.BookController;
import kz.iitu.library.controllers.GenreController;
import kz.iitu.library.controllers.UserController;
import kz.iitu.library.models.*;
import kz.iitu.library.repo.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LibraryApplication.class);
        UserController userController = context.getBean("userController", UserController.class);
        BookController bookController = context.getBean("bookController", BookController.class);
        GenreController genreController = context.getBean("genreController", GenreController.class);

        userController.addAuthor(new Author("Lev Tolstoy"));
        userController.addAuthor(new Author("Mukhtar Auezov"));

        genreController.addGenre(new Genre("Documental"));

        Book book = new Book();
        book.setTitle("Abay zholy");
        bookController.addBook(book);
        book.setAuthors(Collections.singletonList(userController.findAuthorByName("Mukhtar Auezov")));
        book.setGenres(Collections.singletonList(genreController.findGenreByName("Documental")));
        bookController.editBook(book);

        Book book1 = new Book();
        book1.setTitle("Voina i mir");
        bookController.addBook(book1);
        book1.setAuthors(Collections.singletonList(userController.findAuthorByName("Lev Tolstoy")));
        book1.setGenres(Collections.singletonList(genreController.findGenreByName("Documental")));
        bookController.editBook(book1);

        User user = new User();
        user.setName("Dauren Buribekov");
        userController.addUser(user);

//        User user = userController.findUserByName("Dauren Buribekov");
//        Book book = bookController.findBookByName("Voina i mir");

        System.out.println(bookController.findBookByName("Voina i mir"));
        bookController.findAllByStatus(Status.AVAILABLE);
        userController.addBook(user.getId(), book.getId());
        bookController.findAllByStatus(Status.REQUESTED);
        bookController.addBookToUser(user.getId(), book.getId());
        bookController.findAllByStatus(Status.ISSUED);

        bookController.clear();
        genreController.clear();
        userController.clear();
    }
}
