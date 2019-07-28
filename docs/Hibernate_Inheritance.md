
Inheritance in Hibernate:

Single Table strategy:
When we try to save entity that is extending from a superclass, Hibernate, by default uses single table strategy. In this strategy, hibernate doesnot create separate tables for the subclasses, instead only single table is created with a column "dtype". "dtype" is the name of the subclass which is used to identify its instance in the table. All the fields in the N subclasses become the columns of the table. For example, all the columns in TwoWheeler and Fourwheeler subclass will be part "Vehicle" table and as a result, multiple columns can be null for one row. Dtype is the discriminator type.

In single table strategy, We can use @DiscriminatorColumn(name = ,discriminatorType = DiscriminatorType.STRING) and @@DiscriminatorValue to customize discriminator column in the inheritance relationship.

InheritanceType 
Single - default
Table Per Class - table for each class is created.And the data is stored as rows in the individual child tables()
Joined - Normalized, the values/data are stored in parent table as a row and is reffered by the rows in child table(by using id as foreign key). 

CRUD:
session.save()
session.persist()
session.get()
session.delete(object)  - need to pass the object that needs to be deleted, hibernate findsById to find the object 
session.update()

Transient Object and Persistent Object:
Transient Object is in hibernate session but not saved/persisted.
Object is Persisted when session is committed.
Hibernate tracks the object for changes.
Once session is closed, object becomes detached. 



