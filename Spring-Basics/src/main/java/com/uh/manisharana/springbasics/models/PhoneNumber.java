package com.uh.manisharana.springbasics.models;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {

  @Column(name = "COUNTRYCODE")
  private int countryCode;
  private long number;

  @Column(name = "AREACODE")
  private int areaCode;

  public PhoneNumber(int countryCode, long number, int areaCode) {
    this.countryCode = countryCode;
    this.number = number;
    this.areaCode = areaCode;
  }

  public PhoneNumber() {
  }

  public int getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(int countryCode) {
    this.countryCode = countryCode;
  }

  public long getNumber() {
    return number;
  }

  public void setNumber(long number) {
    this.number = number;
  }

  public int getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(int areaCode) {
    this.areaCode = areaCode;
  }
}
