package kz.iitu.library.services.interfaces;

import kz.iitu.library.models.Car;
import kz.iitu.library.models.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarServiceInt {
    boolean addCar(Car car);

    boolean addCarToUser(Long userId, Long bookId);

    boolean returnCarToUser(Long userId, Long bookId);

    List<Car> findAllByStatus(Status status);

    Car findCarByName(String title);

    Car findCarById(Long id);

    List<Car> findAll();

    void save(Car car);

    void clear();

}