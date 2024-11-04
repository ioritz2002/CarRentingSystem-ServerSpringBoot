package com.ioritz.CarRentingSystem_SERVER.service;

import com.ioritz.CarRentingSystem_SERVER.model.Car;
import com.ioritz.CarRentingSystem_SERVER.model.Customer;
import com.ioritz.CarRentingSystem_SERVER.repository.CarRepository;
import com.ioritz.CarRentingSystem_SERVER.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This is the customer service class
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    /**
     * This method create a customer.
     * @param customer The customer to save.
     * @return The created customer.
     */
    public Customer createCustomer(Customer customer){
        logger.info("Creating a customer");
        Customer cust = customerRepository.save(customer);
        logger.info("Customer created.");
        return customerRepository.save(cust);
    }

    /**
     * This method search the customer by the id
     * @param id The id of the customer to search.
     * @return If the customer is found then returns the customer, if not founded then returns a RuntimeException.
     * @throws RuntimeException in case that customer cannot be found by de Id.
     */
    public Customer getCustomerById(Long id) throws RuntimeException{
        logger.info("Searching the customer with the ID: {}", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Customer not found");
                    return new RuntimeException("Customer not found");
                });
    }

    /**
     * This method search customer by the email
     * @param email the email of the customer to search
     * @return the customer with that email.
     */
    public Customer getCustomerByMail(String email){
        logger.info("Searching the customer with the Email: {}", email);
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isEmpty()){
            logger.warn("Customer not found");
            throw new RuntimeException("Customer not found");
        }

        return optionalCustomer.get();
    }


    /**
     * Updates the customer.
     * @param id The id of the customer to update.
     * @param updatedCustomer The customer with the updated info to persist.
     * @return the updated customer.
     */
    public Customer updateCustomer(Long id, Customer updatedCustomer){
        Customer customer = getCustomerById(id);
        logger.info("Updating customers info.");

        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setAddress(updatedCustomer.getAddress());

        logger.info("Customer info updated in the object, now persisting the change");
        return customerRepository.save(customer);
    }

    /**
     * The method that deletes the customer by ID.
     * @param id The id of the customer to delete.
     */
    public void deleteCustomer(Long id){
        logger.info("Deleting the customer with ID: {}", id);
        customerRepository.deleteById(id);
    }

    /**
     * This method is for the rent car operation for the customer
     * @param customerId the id of the customer who wants to rent a car.
     * @param carId the id of the car that the customer whants to rent.
     * @return The customer with the rented car.
     * @throws RuntimeException This exception is thrown in the following cases:
     * <ul>
     *     <li>In case that the customer dont have any car rented</li>
     *     <li>In case that the car is not found</li>
     *     <li>In case that the car is not available to rent</li>
     * </ul>
     *
     */
    public Customer rentCar(Long customerId, Long carId) throws RuntimeException{
        Customer customer = getCustomerById(customerId);
        if(customer.getCar() != null){
            logger.warn("The customer allready has rented a car");
            throw new RuntimeException("The client allready has rented a car");

        }

        logger.info("Searching for the car with ID: {}", carId);
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> {
                    logger.info("Car not found");
                    return new RuntimeException("Car not found");
                });

        if(!car.getAvailable()){
            logger.warn("Car is not available to rent");
            throw new RuntimeException("The car is not available to rent");
        }

        logger.info("Assigning the rented car to the customer and saving all the information.");
        customer.setCar(car);
        car.setAvailable(false);
        carRepository.save(car);

        return customerRepository.save(customer);
    }

    /**
     * This method is for returning the car operation for the customer.
     * @param customerId The id of the customer who has the car rented.
     * @return The cusomer without the rented car.
     * @throws RuntimeException This exception is thrown in the case that the customer don't have rented a car.
     */
    public Customer returnCar(Long customerId) throws RuntimeException{
        Customer customer = getCustomerById(customerId);
        Car car = customer.getCar();
        if(car == null){
            logger.warn("The client dont have rented any car");
            throw new RuntimeException("The customer dont have rented any car");
        }

        logger.info("Returning the car and setting all thing to correct value");
        customer.setCar(null);
        car.setAvailable(true);
        carRepository.save(car);

        return customerRepository.save(customer);
    }

    /**
     * This method is for getting the info about the rented car of the customer.
     * @param customerId The id of the customer who has rented the car
     * @return Returns the car that the customer has rented.
     * @throws RuntimeException This exception is thrown if the customer don't have any car rented.
     */
    public Car getRentedCar(Long customerId) throws RuntimeException{
        logger.info("Getting information about the rented car.");
        Customer customer = getCustomerById(customerId);
        Car car = customer.getCar();

        if(car == null) {
            logger.warn("The customer don't have any car rented.");
            throw new RuntimeException("The customer don't have any car rented.");
        }
        return car;
    }



}
