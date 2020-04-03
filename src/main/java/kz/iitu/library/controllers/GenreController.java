package kz.iitu.library.controllers;

import kz.iitu.library.models.Genre;
import kz.iitu.library.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GenreController {
    @Autowired
    private GenreService genreService;

    public void addGenre(Genre genre){
        if(genreService.addGenre(genre)){
            System.out.println("Жанр добавлен");
            return;
        }
        System.out.println("Error");
        return;
    }

    public Genre findGenreByName(String name){
        return genreService.findGenreByName(name);
    }

    public void clear() {
        genreService.clear();
    }
}
