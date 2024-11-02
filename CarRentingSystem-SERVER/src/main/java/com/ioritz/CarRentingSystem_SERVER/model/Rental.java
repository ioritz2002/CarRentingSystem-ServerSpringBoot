package com.ioritz.CarRentingSystem_SERVER.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * This class modelates the rental of a car
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rental {
    /**
     * The id of the rental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The rented car
     */
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;


    /**
     * The customer who rents a car
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * The rent start day
     */
    private LocalDate startDate;
    /**
     * The date of the end of the rent
     */
    private LocalDate endDate;
    /**
     * Total price of the rent
     */
    private Double totalPrice;

}
