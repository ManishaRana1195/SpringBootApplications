package com.example.manisharana.DribbleClone.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private long id;

  private String title;
  private String description;
  private PostType type;

  @ManyToMany(mappedBy = , fetch = FetchType.LAZY)
  private List<Tag> tags;
  private long numberOfViews;
  private long numberOfLikes;
  private long numberOfSaves;
  private Date createdOn;



  public void setId(long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setType(PostType type) {
    this.type = type;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public void setNumberOfViews(long numberOfViews) {
    this.numberOfViews = numberOfViews;
  }

  public void setNumberOfLikes(long numberOfLikes) {
    this.numberOfLikes = numberOfLikes;
  }

  public void setNumberOfSaves(long numberOfSaves) {
    this.numberOfSaves = numberOfSaves;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }
}
