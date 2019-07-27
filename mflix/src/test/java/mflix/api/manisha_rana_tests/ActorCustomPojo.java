package mflix.api.manisha_rana_tests;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;

public class ActorCustomPojo {

  @BsonProperty("_id")
  private String id;
  private String name;

  @BsonProperty("date_of_birth")
  private Date dateOfBirth;

  @BsonProperty("num_of_movies")
  private int numOfMovies;

  public ActorCustomPojo() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public int getNumOfMovies() {
    return numOfMovies;
  }

  public void setNumOfMovies(int numOfMovies) {
    this.numOfMovies = numOfMovies;
  }

  public ActorCustomPojo withNewId() {
    setId(new ObjectId().toHexString());
    return this;
  }
}
