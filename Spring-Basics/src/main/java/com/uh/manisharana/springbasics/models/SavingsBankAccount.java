package com.uh.manisharana.springbasics.models;

import javax.persistence.Entity;

@Entity
public class SavingsBankAccount extends BankAccount {

  private int minimumBalanceAmount;
  private int maximumWithdrawAmount;

  public SavingsBankAccount(int accountId, String bankName, int minimumBalanceAmount, int maximumWithdrawAmount) {
    super(accountId, bankName);
    this.minimumBalanceAmount = minimumBalanceAmount;
    this.maximumWithdrawAmount = maximumWithdrawAmount;
  }

  public SavingsBankAccount() {
  }
}
