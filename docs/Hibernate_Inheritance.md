
Inheritance in Hibernate:

Single Table strategy:
When we try to save entity that is extending from a superclass, Hibernate, by default uses single table strategy. In this strategy, hibernate doesnot create separate tables for the subclasses, instead only single table is created with a column "dtype". "dtype" is the name of the subclass which is used to identify its instance in the table. All the fields in the N subclasses become the columns of the table. For example, all the columns in TwoWheeler and Fourwheeler subclass will be part "Vehicle" table and as a result, multiple columns can be null for one row. Dtype is the discriminator type.
