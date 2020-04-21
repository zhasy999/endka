package kz.iitu.library.services;

import kz.iitu.library.models.*;
import kz.iitu.library.repo.BookRepository;
import kz.iitu.library.repo.UserRepository;
import kz.iitu.library.services.interfaces.BookServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookService implements BookServiceInt {
    private BookRepository bookRepository;
    private UserRepository userRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Book> findAllByStatus(Status status) {
        for (Book b : bookRepository.findAll()) {
            Date date = new Date();
            if (b.getDueDate() != null) {
                if (b.getDueDate().before(date)) {
                    b.setStatus(Status.OVERDUE);
                    bookRepository.save(b);
                }
            }
        }
        return bookRepository.findAllByStatus(status);
    }
    @Transactional
    public Book findBookByName(String title) {
        return bookRepository.findBookByTitleIgnoreCase(title);
    }
    @Transactional
    public Book findBookById(Long id) {
        return bookRepository.findById(id).get();
    }
    @Transactional
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        return books;
    }
    @Transactional
    public List<Book> findAllByAuthor(Author author) {
        return bookRepository.findAllByAuthorsContaining(author);
    }





    @Transactional
    public boolean addBook(Book book) {
        if (bookRepository.findBookByTitleIgnoreCase(book.getTitle()) != null) {
            book.setId(Long.MIN_VALUE);
            System.out.println("Error");
            return false;
        }
        book.setStatus(Status.AVAILABLE);
        bookRepository.save(book);
        return true;
    }
    @Transactional
    public boolean addBookToUser(Long userId, Long bookId) {
        if (bookRepository.findById(bookId).isEmpty() || userRepository.findById(userId).get() == null) {
            System.out.println("Нету таких данных");
            return false;
        }
        if (bookRepository.findById(bookId).get().getUser().getId() != userId) {
            System.out.println("Этот клиент не запрашивал книгу");
            return false;
        }
        Book book = bookRepository.findById(bookId).get();
        book.setUser(userRepository.findById(userId).get());
        book.setStatus(Status.ISSUED);

        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
//        if(userRepository.findById(userId).get().getType() == Type.NEWBIE) instance.add(Calendar.DAY_OF_MONTH, 14);
//        if(userRepository.findById(userId).get().getType() == Type.EXPERT) instance.add(Calendar.DAY_OF_MONTH, 30);

        Date newDate = instance.getTime();
        book.setGivenDate(date);
        book.setDueDate(newDate);

        bookRepository.save(book);
        return true;
    }
    @Transactional
    public boolean returnBookFromUser(Long userId, Long bookId) {
        if (bookRepository.findById(bookId).isEmpty() || userRepository.findById(userId).get() == null) {
            System.out.println("Error");
            return false;
        }
        if (bookRepository.findById(bookId).get().getUser() != userRepository.findById(userId).get()) {
            System.out.println("Error");
            return false;
        }
        Book book = bookRepository.findById(bookId).get();
        book.setUser(null);
        book.setDueDate(null);
        book.setGivenDate(null);
        book.setStatus(Status.AVAILABLE);
        bookRepository.save(book);
        return true;
    }
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }


    @Transactional
    public void clear() {
        for (Book b : bookRepository.findAll()) {
            b.setGenres(null);
            b.setAuthors(null);
            bookRepository.save(b);
        }
        bookRepository.deleteAll();
    }

    @Override
    public Long deleteBookByName(String title) {
        return bookRepository.deleteBookByTitleIgnoreCase(title);
    }
}
