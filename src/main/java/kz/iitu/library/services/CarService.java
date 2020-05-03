package kz.iitu.library.services;

import kz.iitu.library.models.*;
import kz.iitu.library.repo.CarRepository;
import kz.iitu.library.repo.ModelRepository;
import kz.iitu.library.repo.UserRepository;
import kz.iitu.library.services.interfaces.CarServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarService implements CarServiceInt {
    private CarRepository carRepository;
    private UserRepository userRepository;
    private ModelRepository modelRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Car findCarByName(String title) {
        return carRepository.findCarByTitleIgnoreCase(title);
    }
    @Transactional
    public Car findCarById(Long id) {
        return carRepository.findById(id).get();
    }
    @Transactional
    public List<Car> findAll() {
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    @Transactional
    public boolean addCar(Car car) {
        if (carRepository.findCarByTitleIgnoreCase(car.getTitle()) != null) {
            car.setId(Long.MIN_VALUE);
            System.out.println("Error");
            return false;
        }
        car.setStatus(Status.FIXING);
        carRepository.save(car);
        return true;
    }
    @Transactional
    public boolean addCarToUser(Long userId, Long carId) {
        if (carRepository.findById(carId).isEmpty() || userRepository.findById(userId).get() == null) {
            System.out.println("Нету таких данных");
            return false;
        }
        Car car = carRepository.findById(carId).get();
        car.setUser(userRepository.findById(userId).get());
        car.setStatus(Status.FIXING);

        carRepository.save(car);
        return true;
    }
    @Transactional
    public boolean addModelToCar(Long modelId, Long carId) {
        if (carRepository.findById(carId).isEmpty() || modelRepository.findById(modelId).get() == null) {
            System.out.println("Нету таких данных");
            return false;
        }
        Car car = carRepository.findById(carId).get();
        car.setModel(modelRepository.findById(modelId).get());

        carRepository.save(car);
        return true;
    }
    @Transactional
    public boolean returnCarToUser(Long userId, Long carId) {
        if (carRepository.findById(carId).isEmpty() || userRepository.findById(userId).get() == null) {
            System.out.println("Error");
            return false;
        }
        if (carRepository.findById(carId).get().getUser() != userRepository.findById(userId).get()) {
            System.out.println("Error");
            return false;
        }
        Car car = carRepository.findById(carId).get();
        car.setUser(null);
        car.setStatus(Status.READY);
        carRepository.save(car);
        return true;
    }

    @Transactional
    public List<Car> findAllByStatus(Status status) {
        return carRepository.findAllByStatus(status);
    }

    @Transactional
    public void save(Car car) {
        carRepository.save(car);
    }


    @Transactional
    public void clear() {
        for (Car b : carRepository.findAll()) {
            b.setModel(null);
            carRepository.save(b);
        }
        carRepository.deleteAll();
    }

}
