package kz.iitu.library.services;

import kz.iitu.library.models.Genre;
import kz.iitu.library.repo.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository){
        this.genreRepository=genreRepository;
    }

    @Transactional
    public boolean addGenre(Genre genre) {
        if (genreRepository.findGenreByNameIgnoreCase(genre.getName()) != null)
            return false;
        genreRepository.save(genre);
        return true;

    }
    @Transactional
    public List<Genre> findAll(){
        return (List<Genre>) genreRepository.findAll();
    }
    @Transactional
    public Genre findGenreByName(String name){
        return genreRepository.findGenreByNameIgnoreCase(name);
    }
    @Transactional
    public Long deleteByGenreName(String name){
        return genreRepository.deleteGenreByNameIgnoreCase(name);
    }
    @Transactional
    public void clear(){
        genreRepository.deleteAll();
    }
}
