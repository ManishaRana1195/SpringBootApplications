package com.uh.manisharana.springbasics.models;


import javax.persistence.*;
import java.util.Date;

@Entity
public class UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private String emailId;

  @Temporal(TemporalType.DATE)
  private Date dateOfBirth;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "countryCode", column = @Column(name = "homeCountryCode")),
      @AttributeOverride(name = "number", column = @Column(name = "homeNumber")),
      @AttributeOverride(name = "areaCode", column = @Column(name = "homeAreaCode"))})
  private PhoneNumber homePhoneNumber;

  @Embedded
  private PhoneNumber officePhoneNumber;


  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public PhoneNumber getHomePhoneNumber() {
    return homePhoneNumber;
  }

  public void setHomePhoneNumber(PhoneNumber homePhoneNumber) {
    this.homePhoneNumber = homePhoneNumber;
  }

  public PhoneNumber getOfficePhoneNumber() {
    return officePhoneNumber;
  }

  public void setOfficePhoneNumber(PhoneNumber officePhoneNumber) {
    this.officePhoneNumber = officePhoneNumber;
  }
}
