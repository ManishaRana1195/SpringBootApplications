package com.uh.manisharana.springbasics;

import com.uh.manisharana.springbasics.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication
public class SpringBasicsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBasicsApplication.class, args);
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session currentSession = sessionFactory.openSession();
    UserDetails userDetails = new UserDetails();
    userDetails.setName("Serena williams");
    userDetails.setDateOfBirth(new Date());
    userDetails.setEmailId("Serena@williams.eu");

    PhoneNumber home = new PhoneNumber(91, 505050, 8050);
    PhoneNumber office = new PhoneNumber(1, 505050, 5050);
    userDetails.setHomePhoneNumber(home);
    userDetails.setOfficePhoneNumber(office);

    Address address = new Address();
    address.setStreet("Sixth Avenue");
    address.setCity("Omaha");
    address.setState("Texas");
    address.setZipCode(500617L);


    BankAccount bankAccount = new BankAccount(100304,"Chase");
    CheckingBankAccount checkingAccount = new CheckingBankAccount(190078, "Chase", 1200, 2000);
    SavingsBankAccount savingsAccount = new SavingsBankAccount(290078, "Chase", 1500, 1500);
    currentSession.beginTransaction();
    currentSession.save(bankAccount);
    currentSession.save(checkingAccount);
    currentSession.save(savingsAccount);


    currentSession.save(address);
    userDetails.setAddress(address);
    currentSession.save(userDetails);
    currentSession.
    currentSession.getTransaction().commit();

  }

}
