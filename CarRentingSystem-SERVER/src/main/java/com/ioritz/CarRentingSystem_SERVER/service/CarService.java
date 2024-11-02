package com.ioritz.CarRentingSystem_SERVER.service;

import com.ioritz.CarRentingSystem_SERVER.model.Car;
import com.ioritz.CarRentingSystem_SERVER.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the car service class
 */
@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    /**
     * A method to create a car
     * @param car the car to create
     * @return Returns the car that is going to be created.
     */
    public Car createCar(Car car){
        logger.info("Creating a new car: {}", car);
        Car savedCar = carRepository.save(car);
        logger.debug("Car saved with ID: {}", savedCar.getId());
        return savedCar;
    }

    /**
     * A method to find a car by the car id
     * @param id The id of the car
     * @return If the car exists returns the car, if not exists returns  a runtime exteption.
     */
    public Car getCarById(Long id){
        logger.info("Searching for a car with ID: {}", id);
        return carRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Car with ID {} not found", id);
                    return new RuntimeException("Car not found");
                });
    }

    public Car updateCar(Long id, Car updatedCar){
        logger.info("Updating the car");

        Car car = getCarById(id);
        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setColor(updatedCar.getColor());
        car.setLicensePlate(updatedCar.getLicensePlate());
        car.setAvailable(updatedCar.getAvailable());
        car.setDailyRate(updatedCar.getDailyRate());
        car = carRepository.save(car);

        logger.info("Car updated!");

        return car;
    }

}
