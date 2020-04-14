package kz.iitu.library.services.interfaces;

import kz.iitu.library.models.Genre;
import org.springframework.stereotype.Service;

@Service
public interface GenreServiceInt {
    boolean addGenre(Genre genre);
    Genre findGenreByName(String name);
    void clear();
    boolean deleteByGenreName(String name);
}
