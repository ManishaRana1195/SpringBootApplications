package com.uh.manisharana.springmvcrest.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String emailId;

  public void setName(String name) {
    this.name = name;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }
}
