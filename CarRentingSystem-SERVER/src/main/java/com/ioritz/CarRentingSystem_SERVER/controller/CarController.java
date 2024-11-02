package com.ioritz.CarRentingSystem_SERVER.controller;

import com.ioritz.CarRentingSystem_SERVER.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ioritz.CarRentingSystem_SERVER.service.CarService;

/**
 * This is the RestController for the car entity
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * Creates a car
     * @param car the car to create
     * @return the created car
     */
    @PostMapping
    public Car createCar(@RequestBody Car car){
        return carService.createCar(car);
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id){
        return carService.getCarById(id);
    }


}
