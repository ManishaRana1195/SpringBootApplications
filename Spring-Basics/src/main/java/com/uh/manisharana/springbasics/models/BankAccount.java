package com.uh.manisharana.springbasics.models;

import javax.persistence.*;

@Entity
@Table(name = "BANK_ACCOUNT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BankAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int accountId;
  private String bankName;

  public BankAccount(int accountId, String bankName) {
    this.accountId = accountId;
    this.bankName = bankName;
  }

  public BankAccount() {
  }
}
