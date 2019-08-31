###JPA
1. What is JPA?
   Java Persistence API. It maps directly the object to the table
   
2. What is the problem solved by JPA - Object Relational Impedance?   
   Object Relational Impedance is difference between how the objects are mapped in Java and how they are stored in database. Object oriented programming involves concepts as polymorphism,
   inheritance and interfaces whereas tables in database are normalized. 
   There could be column names in table that could be different from field names in POJO.
   POJO classes can have relationship inheritance. 
   
3. What are the alternatives to JPA?
   JDBC         - The values are set in the prepared statement using POJO setters and result is converted into POJO format by reading each field from ResultSet.
   Spring JDBC  - It is layer on top of JDBC and has JDBC templates. Still you need to implement RowMapper to map result to Object. 
   myBatis      - MyBatis removes the need for manually writing code to set parameters and retrieve results. It provides simple XML or Annotation based configuration to map Java POJOs to database.
   
4. What is Hibernate and How does it relate to JPA?
    
5. What is Spring Data JPA?
6. How to create a simple JPA project using Spring Boot Data JPA Starter?
