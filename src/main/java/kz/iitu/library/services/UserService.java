package kz.iitu.library.services;

import kz.iitu.library.controllers.UserController;
import kz.iitu.library.models.Author;
import kz.iitu.library.models.Book;
import kz.iitu.library.models.Status;
import kz.iitu.library.models.User;
import kz.iitu.library.repo.AuthorRepository;
import kz.iitu.library.repo.BookRepository;
import kz.iitu.library.repo.UserRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;



    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }
@Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @Transactional
    public boolean addUser(User user){
        if(userRepository.findUserByName(user.getName())!=null)
            return false;
        userRepository.save(user);
        return true;
    }
    @Transactional
    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    public boolean addAuthor(Author author) {
        if(authorRepository.findAuthorByName(author.getName())!=null)
            return false;
        authorRepository.save(author);
        return true;
    }

    @Transactional
    public Author findAuthorByName(String name) {
        return authorRepository.findAuthorByName(name);
    }

    @Transactional
    public User findUserByName(String name) {
        return userRepository.findUserByName(name);
    }
    @Transactional
    public void clear() {
        userRepository.deleteAll();
        authorRepository.deleteAll();
    }

    public void addBook(Long userId, Long bookId) {
        if(bookId<0){
            System.out.println("Error");
            return;
        }
        Book book = bookRepository.findById(bookId).get();
        book.setStatus(Status.REQUESTED);
        book.setUser(userRepository.findById(userId).get());
        bookRepository.save(book);
    }
}
