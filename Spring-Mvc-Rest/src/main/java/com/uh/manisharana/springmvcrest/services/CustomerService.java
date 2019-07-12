package com.uh.manisharana.springmvcrest.services;

import com.uh.manisharana.springmvcrest.domain.Customer;

import java.util.List;

public interface CustomerService {

  Customer findCustomerById(long id);
  List<Customer> findAllCustomers();
}
