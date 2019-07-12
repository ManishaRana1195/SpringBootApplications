package com.uh.manisharana.springmvcrest.main;

import com.uh.manisharana.springmvcrest.domain.Customer;
import com.uh.manisharana.springmvcrest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

  @Autowired
  private CustomerService customerService;

  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Loading initial customer data into ");

    Customer billal = new Customer();
    billal.setName("Billal");
    billal.setEmailId("blaahBa@yahoo.com");
    customerService.saveCustomer(billal);

    System.out.println("Total customers: " + customerService.countCustomers());

  }
}
