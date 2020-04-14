package kz.iitu.library.controllers;

import kz.iitu.library.models.Genre;
import kz.iitu.library.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("/{genrename}")
    public Genre findGenreByName(@PathVariable String genrename) {
        return genreService.findGenreByName(genrename);
    }

    @GetMapping
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @PostMapping("/addGenre")
    public String addGenre(@RequestBody Genre genre) {
        if (genreService.addGenre(genre)) {
            return (genre + "Жанр добавлен");
        }
        return ("Такой жанр уще существует");
    }

    @DeleteMapping("/deleteGenre/{genrename}")
    public boolean deleteGenreByName(@PathVariable String genrename) {
        if (genreService.deleteByGenreName(genrename) > 0) {
            return true;
        }
        return false;
    }

    @DeleteMapping("/cleanGenres")
    public void clear() {
        genreService.clear();
    }
}
