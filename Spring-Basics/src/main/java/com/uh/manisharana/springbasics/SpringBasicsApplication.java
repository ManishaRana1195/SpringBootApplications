package com.uh.manisharana.springbasics;

import com.uh.manisharana.springbasics.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBasicsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBasicsApplication.class, args);
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session currentSession = sessionFactory.openSession();
    User user = new User();
    user.setName("Serena williams");
    currentSession.save(user);
  }

}
