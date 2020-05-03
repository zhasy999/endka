package kz.iitu.library.services.interfaces;

import kz.iitu.library.models.Model;
import org.springframework.stereotype.Service;

@Service
public interface ModelServiceInt {
    boolean addGenre(Model model);
    Model findModelByName(String type);
    void clear();
    boolean deleteByGenreName(String type);
}
