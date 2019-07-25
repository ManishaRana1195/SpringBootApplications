package mflix.api.manisha_rana_tests;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class ActorBasic {

  @BsonProperty(value = "_id")
  private ObjectId id;
  private String name;
  @BsonProperty(value = "date_of_birth")
  private Date dateOfBirth;
  private List<Document> awards;
  @BsonProperty(value = "num_of_movies")
  private int numOfMovies;

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
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

  public List<Document> getAwards() {
    return awards;
  }

  public void setAwards(List<Document> awards) {
    this.awards = awards;
  }

  public int getNumOfMovies() {
    return numOfMovies;
  }

  public void setNumOfMovies(int numOfMovies) {
    this.numOfMovies = numOfMovies;
  }
}
