package kz.iitu.library.repo;

import kz.iitu.library.models.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends CrudRepository<Model, Long>{
    Model findModelByNameIgnoreCase(String name);
    Model findModelById(Long id);
}
