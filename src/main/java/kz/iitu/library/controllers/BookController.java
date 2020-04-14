package kz.iitu.library.controllers;

import kz.iitu.library.models.*;
import kz.iitu.library.services.BookService;
import kz.iitu.library.services.GenreService;
import kz.iitu.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<Book> allBooks(){
        return bookService.findAll();
    }
    @GetMapping("/status={status}")
    public List<Book> findAllByStatus(@PathVariable Status status){
       return bookService.findAllByStatus(status);
    }
    @GetMapping("/{id}")
    public Book findBookByName(@PathVariable Long id) {
        return bookService.findBookById(id);
    }
    @GetMapping("/author={authorname}")
    public List<Book> findBookByAuthor(@PathVariable String authorname){
        return bookService.findAllByAuthor(userService.findAuthorByName(authorname));
    }

    @PostMapping
    public String addBook(@RequestBody Book book) {
        if (bookService.addBook(book)) {
            return ("Книга " + book + " добавлена");
        }
        return (book + " Такая книга уже есть");

    }

    @PutMapping("/{id}")
    public String editBook(@PathVariable Long id, @RequestParam String authorname, @RequestParam String genrename) {
        Book book = bookService.findBookById(id);
        List<Genre> genres = new ArrayList<Genre>();
        List<Author> authors = new ArrayList<Author>();
        authors.add(userService.findAuthorByName(authorname));
        genres.add(genreService.findGenreByName(genrename));
        book.setGenres(genres);
        book.setAuthors(authors);
        bookService.save(book);
        return ("Книга "+ book + " переработано");
    }

    @PostMapping("/addBook")
    public String addBookToUser(@RequestParam Long userId,@RequestParam Long bookId) {
        if (bookService.addBookToUser(userId, bookId)) {
            bookService.findBookById(bookId).notifyMe();
            return ("Книга добавлена пользователю " + userId);
        }
        return ("Выходить ошибка книга уже кому то присвоена или ее юзер не запрашивал");
    }

    @PostMapping("/returnBook")
    public String returnBookFromUser(@RequestParam Long userId,@RequestParam Long bookId) {
        if (bookService.returnBookFromUser(userId, bookId)) {
            bookService.findBookById(bookId).notifyMe();
            return ("Книга вернулась с пользователя " + userId);
        }
        return ("Ошибка книга уже свободна");
    }

    @DeleteMapping("/deleteBook/{bookname}")
    public boolean deleteBookByName(@PathVariable String bookName) {
        if (bookService.deleteBookByName(bookName) > 0) {
            return true;
        }
        return false;
    }

    @DeleteMapping("/cleanBooks")
    public void clear() {
        bookService.clear();
    }
}
