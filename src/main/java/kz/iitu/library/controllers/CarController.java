package kz.iitu.library.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.iitu.library.models.*;
import kz.iitu.library.services.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/car")
@RestController
public class CarController {

    @Autowired
    private CarService carService;



    @GetMapping
    public List<Car> allCars(){
        return carService.findAll();
    }

    @GetMapping("/status={status}")
    @ApiOperation(  value = "Find cars by status",
            notes = "Write status and find cars",
            response = Car.class)
    public List<Car> findAllByStatus(@ApiParam(value = "value for status", required = true) @PathVariable Status status){
       return carService.findAllByStatus(status);
    }

    @GetMapping("/{id}")
    public Car findCarByName(@PathVariable Long id) {
        return carService.findCarById(id);
    }


    @PostMapping("/create")
    public String addCar(@RequestParam String title) {
        Car car=new Car();
        car.setTitle(title);
        if (carService.addCar(car)) {
            return ("Car " + car + " added");
        }
        return (car + " Already exist");

    }

    @PostMapping("/addCar")
    public String addCarToUser(@RequestParam Long userId,@RequestParam Long carId) {
        if (carService.addCarToUser(userId, carId)) {
            carService.findCarById(carId);
            return ("Car added to " + userId);
        }
        return ("Error");
    }

    @PostMapping("/addModel")
    public String addCarToModel(@RequestParam Long carId,@RequestParam Long modelId) {
        if (carService.addModelToCar(modelId, carId)) {
            carService.findCarById(carId);
            return ("Car added to " + modelId);
        }
        return ("Error");
    }

    @PostMapping("/returnCar")
    public String returnCarToUser(@RequestParam Long userId,@RequestParam Long carId) {
        if (carService.returnCarToUser(userId, carId)) {
            carService.findCarById(carId);
            return ("Car Returned to " + userId);
        }
        return ("Error");
    }



    @DeleteMapping("/cleanCars")
    public void clear() {
        carService.clear();
    }
}
