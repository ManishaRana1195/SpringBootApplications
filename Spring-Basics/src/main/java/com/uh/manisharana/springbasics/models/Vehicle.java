package com.uh.manisharana.springbasics.models;

import javax.persistence.*;

@Entity
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "MODELNAME")
  private String modelName;

  /*TODO: If you do not specify @column here, hibernate will create 2 column model_year and modelyear*/
  @Column(name = "MODELYEAR")
  private int modelYear;

  @ManyToOne
  private UserDetails userDetails;
}
