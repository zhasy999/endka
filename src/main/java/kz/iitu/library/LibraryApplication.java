package kz.iitu.library;

import kz.iitu.library.controllers.BookController;
import kz.iitu.library.controllers.GenreController;
import kz.iitu.library.controllers.UserController;
import kz.iitu.library.models.Genre;
import kz.iitu.library.models.User;
import kz.iitu.library.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}
