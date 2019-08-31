package com.uh.manisharana.springbasics.models;

import javax.persistence.*;

@Entity
@Table(name = "BANK_ACCOUNT")
@NamedQuery(name = "BankAccount.byAccountId", query = "from BankAccount where accountId = :accountId")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BankAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "account_id")
  private Integer accountId;
  private String bankName;

  public BankAccount(int accountId, String bankName) {
    this.accountId = accountId;
    this.bankName = bankName;
  }

  public BankAccount() {
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }
}
