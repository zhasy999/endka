package kz.iitu.library.repo;

import kz.iitu.library.models.Car;
import kz.iitu.library.models.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAllByStatus(Status status);

    List<Car> findAll();

    Car findCarByTitleIgnoreCase(String title);

}
