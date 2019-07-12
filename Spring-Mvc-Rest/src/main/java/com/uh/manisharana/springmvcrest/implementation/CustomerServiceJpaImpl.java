package com.uh.manisharana.springmvcrest.implementation;

import com.uh.manisharana.springmvcrest.domain.Customer;
import com.uh.manisharana.springmvcrest.repositories.CustomerRepository;
import com.uh.manisharana.springmvcrest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceJpaImpl implements CustomerService {

  private CustomerRepository customerRepository;

  public void setCustomerRepository(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer findCustomerById(long id) {
    return customerRepository.getOne(id);
  }

  @Override
  public List<Customer> findAllCustomers() {
    return customerRepository.findAll();
  }
}
