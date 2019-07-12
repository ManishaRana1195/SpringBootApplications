package com.uh.manisharana.springmvcrest.controllers;

import com.uh.manisharana.springmvcrest.domain.Customer;
import com.uh.manisharana.springmvcrest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = CustomerController.BASE_API)
public class CustomerController {

  static final String BASE_API = "api/v1/customers";

  @Autowired
  private CustomerService customerService;


  @GetMapping("/{id}")
  private Customer getCustomer(@PathVariable long id) {
    return customerService.findCustomerById(id);
  }

  @GetMapping
  private List<Customer> getAllCustomers() {
    return customerService.findAllCustomers();
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  private void saveCustomer(@RequestBody Customer customer) {
    customerService.saveCustomer(customer);
  }

}
