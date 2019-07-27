package mflix.api.manisha_rana_tests;

import org.bson.*;
import org.bson.codecs.*;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class ActorCustomCodec implements CollectibleCodec<ActorCustomPojo> {

  private final Codec<Document> documentCodec;

  ActorCustomCodec() {
    documentCodec = new DocumentCodec();
  }


  @Override
  public boolean documentHasId(ActorCustomPojo actorCustomPojo) {
    return actorCustomPojo.getId() != null;
  }

  @Override
  public ActorCustomPojo generateIdIfAbsentFromDocument(ActorCustomPojo actorCustomPojo) {
    return !documentHasId(actorCustomPojo) ? actorCustomPojo.withNewId() : actorCustomPojo;
  }

  @Override
  public BsonValue getDocumentId(ActorCustomPojo actorCustomPojo) {
    if (!documentHasId(actorCustomPojo)) {
      throw new IllegalStateException("This document doesnt have id");
    }

    return new BsonString(actorCustomPojo.getId());
  }

  @Override
  public Class<ActorCustomPojo> getEncoderClass() {
    return ActorCustomPojo.class;
  }

  /*decode from document to  POJO class*/
  @Override
  public ActorCustomPojo decode(BsonReader bsonReader, DecoderContext decoderContext) {
    Document document = documentCodec.decode(bsonReader, decoderContext);
    ActorCustomPojo actor = new ActorCustomPojo();
    actor.setId(document.getObjectId("_id").toHexString());
    actor.setName(document.getString("name"));
    actor.setDateOfBirth(document.getDate("date_of_birth"));
    actor.setNumOfMovies(document.getInteger("num_of_movies"));
    return actor;
  }

  @Override
  public void encode(BsonWriter bsonWriter, ActorCustomPojo actorCustomPojo, EncoderContext encoderContext) {
    Document actorDoc = new Document();
    String actorId = actorCustomPojo.getId();
    String name = actorCustomPojo.getName();
    Date dateOfBirth = actorCustomPojo.getDateOfBirth();
    int numMovies = actorCustomPojo.getNumOfMovies();

    if (null != actorId) {
      actorDoc.put("_id", new ObjectId(actorId));
    }

    if (null != name) {
      actorDoc.put("name", name);
    }

    if (null != dateOfBirth) {
      actorDoc.put("date_of_birth", dateOfBirth);
    }

    if (0 != numMovies) {
      actorDoc.put("num_of_movies", numMovies);
    }

    documentCodec.encode(bsonWriter, actorDoc, encoderContext);
  }

}
