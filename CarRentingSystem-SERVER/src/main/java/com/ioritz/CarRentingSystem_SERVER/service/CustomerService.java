package com.ioritz.CarRentingSystem_SERVER.service;

import com.ioritz.CarRentingSystem_SERVER.repository.CarRepository;
import com.ioritz.CarRentingSystem_SERVER.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the customer service class
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;
}
