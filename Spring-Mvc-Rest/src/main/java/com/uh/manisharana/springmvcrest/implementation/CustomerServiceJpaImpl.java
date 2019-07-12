package com.uh.manisharana.springmvcrest.implementation;

import com.uh.manisharana.springmvcrest.domain.Customer;
import com.uh.manisharana.springmvcrest.repositories.CustomerRepository;
import com.uh.manisharana.springmvcrest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceJpaImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public void setCustomerRepository(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer findCustomerById(long id) {
    return customerRepository.findById(id).get();
  }

  @Override
  public List<Customer> findAllCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public void saveCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public long countCustomers() {
    return customerRepository.count();
  }
}
