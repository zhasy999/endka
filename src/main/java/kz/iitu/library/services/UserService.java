package kz.iitu.library.services;

import kz.iitu.library.controllers.UserController;
import kz.iitu.library.models.*;
import kz.iitu.library.repo.AuthorRepository;
import kz.iitu.library.repo.BookRepository;
import kz.iitu.library.repo.RoleRepository;
import kz.iitu.library.repo.UserRepository;
import kz.iitu.library.services.interfaces.UserServiceInt;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserServiceInt, UserDetailsService {
    private UserRepository userRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
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
        if(userRepository.findUserByUsernameIgnoreCase(user.getUsername())!=null)
            return false;
        user.setRoles(Collections.singletonList(roleRepository.findRoleByName("USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        return userRepository.deleteUserByUsernameIgnoreCase(name);
    }

    @Override
    public Long deleteAuthorByName(String name) {
        return authorRepository.deleteAuthorByNameIgnoreCase(name);
    }

    @Transactional
    public User findUserByName(String name) {
        return userRepository.findUserByUsernameIgnoreCase(name);
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
        if (userRepository.findById(userId).get().getRoles().contains(roleRepository.findRoleByName("ADMIN"))){
            System.out.println("Нельзя давать книги библиотекарю");
            return false;
        }
        Book book = bookRepository.findById(bookId).get();
        book.setStatus(Status.REQUESTED);
        book.setUser(userRepository.findById(userId).get());
        bookRepository.save(book);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsernameIgnoreCase(username);
        if(user == null){
            throw new UsernameNotFoundException("User: " +username+" has not found");
        }
        return user;
    }
}
