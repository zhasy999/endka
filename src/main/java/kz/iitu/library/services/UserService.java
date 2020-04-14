package kz.iitu.library.services;

import kz.iitu.library.controllers.UserController;
import kz.iitu.library.models.*;
import kz.iitu.library.repo.AuthorRepository;
import kz.iitu.library.repo.BookRepository;
import kz.iitu.library.repo.UserRepository;
import kz.iitu.library.services.interfaces.UserServiceInt;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements UserServiceInt {
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
    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }
    @Transactional
    public boolean addUser(User user){
        if(userRepository.findUserByNameIgnoreCase(user.getName())!=null)
            return false;
        user.setType(Type.NEWBIE);
        userRepository.save(user);
        return true;
    }
    @Transactional
    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    @Transactional
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Transactional
    public boolean addAuthor(Author author) {
        if(authorRepository.findAuthorByNameIgnoreCase(author.getName())!=null)
            return false;
        authorRepository.save(author);
        return true;
    }
    @Transactional
    public Author findAuthorByName(String name) {
        return authorRepository.findAuthorByNameIgnoreCase(name);
    }

    @Override
    public Long deleteUserByName(String name) {
        return userRepository.deleteUserByNameIgnoreCase(name);
    }

    @Override
    public Long deleteAuthorByName(String name) {
        return authorRepository.deleteAuthorByNameIgnoreCase(name);
    }

    @Transactional
    public User findUserByName(String name) {
        return userRepository.findUserByNameIgnoreCase(name);
    }
    @Transactional
    public void clear() {
        userRepository.deleteAll();
        authorRepository.deleteAll();
    }
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
    @Transactional
    public boolean addBook(Long userId, Long bookId) {
        if (userRepository.findById(userId).get().getType() == Type.LIBRARIAN){
            System.out.println("Нельзя давать книги библиотекарю");
            return false;
        }
        Book book = bookRepository.findById(bookId).get();
        book.setStatus(Status.REQUESTED);
        book.setUser(userRepository.findById(userId).get());
        bookRepository.save(book);
        return true;
    }
}
