package com.ioritz.CarRentingSystem_SERVER.controller;

import com.ioritz.CarRentingSystem_SERVER.model.Car;
import com.ioritz.CarRentingSystem_SERVER.model.Customer;
import com.ioritz.CarRentingSystem_SERVER.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    /**
     * POST method for customer
     * @param customer the customer to persist
     * @return The created customer.
     */
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    /**
     * This method search for a customer by the id
     * @param id the id of the customer to search
     * @return the customer.
     */
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        Customer customer = null;
        try{
            customer = customerService.getCustomerById(id);
            logger.info("Customer found");

        } catch (RuntimeException ex){
            logger.warn(ex.getMessage());
        }
        return customer;
    }
    /**
     * This method search for a customer by the email
     * @param email the email of the customer to search
     * @return the customer.
     */
    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@PathVariable String email){
        Customer customer = null;
        try{
            customer = customerService.getCustomerByMail(email);
            logger.info("Customer found");

        } catch (RuntimeException ex){
            logger.warn(ex.getMessage());
        }
        return customer;
    }

    /**
     * This method is to update the customer
     * @param id the id of the customer
     * @param updatedCustomer the customer with the updated information
     * @return the updated customer
     */
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer);
    }
    /**
     * This method is to delete the customer
     * @param id the id of the customer
     */
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    /**
     * This method is for renting a car operation
     * @param customerId the ID of the customer who wants to rent the car
     * @param carId the ID of the car that the customer wants to rent
     * @return the customer with the rented car
     */
    @PostMapping("/{customerId}/rent/{carId}")
    public Customer rentCar(@PathVariable Long customerId, @PathVariable Long carId) {
        Customer customer;
        try {
            customer = customerService.rentCar(customerId, carId);
            return customer;
        } catch (RuntimeException ex){
            logger.warn(ex.getMessage());
        }
        return null;
    }


    /**
     * This method does the operation of returning the rented car
     * @param customerId the id of the customer who rents the car
     * @return the customer without the rented car
     */
    @PostMapping("/{customerId}/return")
    public Customer returnCar(@PathVariable Long customerId) {
        Customer customer = null;
        try{
            customer = customerService.returnCar(customerId);
            logger.info("customer updated and car returned");

            return customer;
        } catch (RuntimeException ex){
            logger.warn(ex.getMessage());
        }
        return customer;
    }


    @GetMapping("/{customerId}/car")
    public Car getRentedCar(@PathVariable Long customerId) {
        Car car;
        try{
            car = customerService.getRentedCar(customerId);
            logger.info("Car info obtained");
            return car;
        } catch (RuntimeException ex){
            logger.warn(ex.getMessage());
        }
        return null;
    }

}
