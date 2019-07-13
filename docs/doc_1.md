

Autowiring is done implicitly by the container. It can be set by name or set by type. Autowiring can be done through xml configuration or annotations. 

Autowiring using annotations,
@Autowired - wires the object by type.
@Resource - wires the object by name.
@Inject - wires the object by type
@Qualifier - narrows type wiring as we provide the bean name.

Spring has loose coupling because of Dependency injection mechanism. It is static mechanism and will be verified during compile time. Loose copuling makes testing easier as we can easily mock the classes.

Application Context - Spring has containers - BeanFactory and ApplicationContext
@Component - annotation 
@ComponentScan
@Autowired
@Qualifier
@Configuration



Packaging type in maven, core java application - a jar file is created. For web application - a war file is created which can be deployed into the server.


JPA - Java Persistence API - Specification to perform Object relational mapping and for the interaction between the entities.Declare the method to findByProperty in your service and the Spring Data JPA will implement the method for you. For Example - findByTopicId where topic is property of Course.

@ManyToMany -

@ManyToOne - 

@OneToMany - 


Hibernate - Implements JPA specs. Solves the issues with mapping fields to columns and relationships. It handles data type conversion from java to DB data type. 
Hibernate APIs - 
First create hibernate.cfg.xml file under resources folder. We specify the database URL, username, password and other details in config file which can be used to hibernate to connect to database.

1. create session factory - SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
2. create session from session factory  - Session currentSession = sessionFactory.getCurrentSession(); or openSession();
3. use session to maiplulate entities - currentSession.save(object);

spring.jpa.show-sql= true flag to show the sql as getting executed on the console.
@Table(name = "User") - Links entity with that table name 
@Entity(name = "UserDetails")  - changes the entity name to given name.

@Transient - annotation to prevent hibernate to add specified column to table and thus not saving that value.
@Temporal(TemporalType.Date) - annotation to save only specified part of the timestamp field.
@Lob  -  if the data typethat needs to be stored cant be stored in regular data type, the it can be saved as Large objects. It can CLOB(character) or BLOB(byte stream)


Spring Boot Actuator - 

commands to create jar and run jar file - mvn clean package
 java --jar jar_location


