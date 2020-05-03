package kz.iitu.library.services;

import kz.iitu.library.models.Model;
import kz.iitu.library.repo.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ModelService {
    private ModelRepository modelRepository;

    @Autowired
    public void setModelRepository(ModelRepository modelRepository){
        this.modelRepository=modelRepository;
    }

    @Transactional
    public boolean addModel(Model model) {
        if (modelRepository.findModelByNameIgnoreCase(model.getName()) != null)
            return false;
        modelRepository.save(model);
        return true;

    }
    @Transactional
    public List<Model> findAll(){
        return (List<Model>) modelRepository.findAll();
    }
    @Transactional
    public Model findModelByName(String type){
        return modelRepository.findModelByNameIgnoreCase(type);
    }
    @Transactional
    public void clear(){
        modelRepository.deleteAll();
    }
}
