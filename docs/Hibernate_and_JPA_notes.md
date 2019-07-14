

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


Suppose we have 2 entities, where one entity has a "Has A" relationship with other entity and at database level, the child model(For example, address) resides in parent model(For example, user), then we say that child is embedded into parent model. 
@Embedded and @Embeddable - We use @Embeddable on the child entity and @Embedded at the child reference in the parent class.

Now, if we have 2 objects of the same embedded entity, we would end up having same column names in the parent table. Eg, zipcode, zipcode - twice!!, whereas it should be home_addr_zipcode and billing_addr_zipcode. So to differentiate the column names for the 2 objects, we need @AttributeOverrides annotation, else we get error "Repeated column in mapping for entity".
 
@AttributeOverrides and @AttributeOverride -  Where first value is the column name in child object that needs to be renamed and second value is the name of the new column
 @AttributeOverrides({
      @AttributeOverride(name = "countryCode", column = @Column(name = "homeCountryCode")),
      @AttributeOverride(name = "number", column = @Column(name = "homeNumber")),
      @AttributeOverride(name = "areaCode", column = @Column(name = "homeAreaCode"))})
 
@EmbeddedId - If the Id is an object of multiple keys, For example Id can be object having userName, emailId and id, the Id field can be another object and is embeddable in parent class.

@ElementCollection - Annotation to save a collection of entities(set, list). The entity in the parent class is marked as @ElementCollection. The entity itself is marked as @Embeddable. If we need to rename the joined table name or the joined table id, we can use @JoinTable annotation which lets us rename the column/ table names created by Spring. 


@OneToOne - if the child entity is in separate table, then we can relate the parent and child entity using this annotation.


Spring Boot Actuator - 

commands to create jar and run jar file - mvn clean package
 java --jar jar_location

