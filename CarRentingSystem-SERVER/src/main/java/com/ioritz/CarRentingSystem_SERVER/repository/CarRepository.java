package com.ioritz.CarRentingSystem_SERVER.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ioritz.CarRentingSystem_SERVER.model.Car;

import java.util.List;

/**
 *
 *This is the car repository
 * @since 0.0.1
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    /**
     * Obtains a list of cars that are avaliable to rent.
     * @return A list of cars avaliable to rent.
     */
    List<Car> findByAvailableTrue();
}
