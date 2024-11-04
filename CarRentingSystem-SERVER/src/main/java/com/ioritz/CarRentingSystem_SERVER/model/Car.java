package com.ioritz.CarRentingSystem_SERVER.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
/**
 * This class modelates a Car
 * @since 0.0.1
 */
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * The id of the car
     */
    private Long id;
    /**
     * The brand of the car
     */
    private String brand;
    /**
     * The model of the car
     */
    private String model;
    /**
     * The year of creation of the car
     */
    private Integer year;
    /**
     * The car color
     */
    private String color;
    /**
     * The license plate of the car
     */
    private String licensePlate;
    /**
     * If the car is avaliable to rent will be true, otherwise will be false
     */
    private Boolean available;
    /**
     * The daily rent rate
     */
    private Double dailyRate;

}
