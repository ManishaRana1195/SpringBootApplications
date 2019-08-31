package com.example.manisharana.DribbleClone.models;

import javax.persistence.*;

@Entity
class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private long id;
}
