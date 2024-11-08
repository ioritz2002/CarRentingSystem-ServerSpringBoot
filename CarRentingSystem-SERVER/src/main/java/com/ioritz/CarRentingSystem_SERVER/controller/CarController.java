package com.ioritz.CarRentingSystem_SERVER.controller;

import com.ioritz.CarRentingSystem_SERVER.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ioritz.CarRentingSystem_SERVER.service.CarService;

import java.util.List;

/**
 * This is the RestController for the car entity
 * @since 0.0.1
 *
 */
@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    /**
     * Creates a car
     * @param car the car to create
     * @return the created car
     */
    @PostMapping
    public Car createCar(@RequestBody Car car){
        logger.info("Creating a car");
        return carService.createCar(car);
    }

    /**
     * Search a car by the id.
     * @param id The id of the car to search.
     * @return The car its id mach with searching id.
     */

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id){
        logger.info("Searching the car with ID: {}", id);
        Car car = null;
        try{
            car = carService.getCarById(id);
            logger.info("Car with ID: {} found", id);

        } catch (RuntimeException ex){
            logger.warn("Car with ID {} not found", id);
        }
        return car;
    }

    /**
     * The method that updates the car.
     * @param id The id of the car to update.
     * @param updatedCar The car with the new information.
     * @return The updated car.
     */
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car updatedCar){
        return carService.updateCar(id, updatedCar);
    }

    /**
     * The method that deletes the car that match with the id.
     * @param id The id of the car to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }

    /**
     * The method that obtains a list of cars that are available to rent.
     * @return The list with the available cars.
     */
    @GetMapping("/available")
    public List<Car> getAvailableCars(){
        return  carService.getAvailableCars();
    }
}
