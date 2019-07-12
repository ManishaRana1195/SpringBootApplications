package com.uh.manisharana.springmvcrest.repositories;

import com.uh.manisharana.springmvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
