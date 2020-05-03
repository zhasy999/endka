package kz.iitu.library.controllers;

import kz.iitu.library.models.Model;
import kz.iitu.library.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @GetMapping("/{name}")
    public Model findModelByName(@PathVariable String name) {
        return modelService.findModelByName(name);
    }

    @GetMapping
    public List<Model> findAll() {
        return modelService.findAll();
    }

    @PostMapping("/addModel")
    public String addGenre(@RequestBody Model model) {
        if (modelService.addModel(model)) {
            return (model + "Model added");
        }
        return ("Model already exist");
    }


    @DeleteMapping("/cleanModels")
    public void clear() {
        modelService.clear();
    }
}
