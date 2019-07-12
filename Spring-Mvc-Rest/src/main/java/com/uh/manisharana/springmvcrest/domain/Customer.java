package com.uh.manisharana.springmvcrest.domain;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Customer {
  private int id;
  private String name;
  private String emailId;
}
