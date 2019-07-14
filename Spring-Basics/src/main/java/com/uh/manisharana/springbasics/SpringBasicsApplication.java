package com.uh.manisharana.springbasics;

import com.uh.manisharana.springbasics.models.Address;
import com.uh.manisharana.springbasics.models.PhoneNumber;
import com.uh.manisharana.springbasics.models.UserDetails;
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

    currentSession.beginTransaction();
    currentSession.save(address);
    userDetails.setAddress(address);
    currentSession.save(userDetails);
    currentSession.getTransaction().commit();

  }

}
