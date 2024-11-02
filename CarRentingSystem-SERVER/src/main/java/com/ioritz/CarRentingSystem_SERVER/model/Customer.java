package com.ioritz.CarRentingSystem_SERVER.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * This class modelates a Customer
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * The id of the customer
     */
    private Long id;
    /**
     * The name of the customer
     */
    private String name;
    /**
     * The email of the customer
     */
    private String email;
    /**
     * The phone of the customer
     */
    private String phone;
    /**
     * The address where the customer lives
     */
    private String address;
}
