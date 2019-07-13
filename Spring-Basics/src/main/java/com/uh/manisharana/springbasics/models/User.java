package com.uh.manisharana.springbasics.models;


import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private String emailId;
  private Date dateOfBirth;
  private Address address;
  private String phoneNumber;


  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
}
