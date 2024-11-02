package com.ioritz.CarRentingSystem_SERVER.repository;

import com.ioritz.CarRentingSystem_SERVER.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is the customer repository
 * @since 0.0.1
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Obtains the Customer by the email.
     * @param email the email to search the customer
     * @return An optional container with the customer.
     */
    Optional<Customer> findByEmail(String email);
}
