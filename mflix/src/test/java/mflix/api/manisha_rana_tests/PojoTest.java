package mflix.api.manisha_rana_tests;


import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mflix.api.daos.TicketTest;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EnableAutoConfiguration
@EnableConfigurationProperties
@RunWith(SpringJUnit4ClassRunner.class)
public class PojoTest extends TicketTest {

  private String mongoDbUri;
  private MongoClient mongoClient;
  private MongoDatabase testDb;

  @Before
  public void setUp() throws ParseException, IOException {
    ArrayList<Document> actors = getActorDocuments();

    // insert data into db
    mongoDbUri = getProperty("spring.mongodb.uri");
    mongoClient = MongoClients.create(mongoDbUri);
    testDb = mongoClient.getDatabase("testDb");
    MongoCollection<Document> actorsCollection = testDb.getCollection("actors");

    actorsCollection.insertMany(actors);

  }

  private ArrayList<Document> getActorDocuments() throws ParseException {
    HashMap<String, Object> bruceMap = new HashMap<>();
    bruceMap.put("name", "Bruce Campbell");
    bruceMap.put("date_of_birth", new SimpleDateFormat("yyyy-mm-dd").parse("1958-06-22"));
    bruceMap.put("awards", Collections.EMPTY_LIST);
    bruceMap.put("num_of_movies", 127);

    HashMap<String, Object> natalieMap = new HashMap<>();
    natalieMap.put("name", "Natalie Portman");
    natalieMap.put("date_of_birth", new SimpleDateFormat("yyyy-mm-dd").parse("1981-06-22"));
    natalieMap.put("awards", Collections.EMPTY_LIST);
    natalieMap.put("num_of_movies", 63);

    Document bruceCampbell = new Document(bruceMap);
    Document nataliePortman = new Document(natalieMap);

    ArrayList<Document> actors = new ArrayList<>();
    actors.add(bruceCampbell);
    actors.add(nataliePortman);
    return actors;
  }

  @Test
  public void testParsingPojoWithDefaultCodec() {
    CodecRegistry defaultCodec = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    MongoCollection<ActorBasic> actors = testDb.getCollection("actors", ActorBasic.class).withCodecRegistry(defaultCodec);


    Bson filter = eq("name", "Bruce Campbell");
    ActorBasic actorBasic = actors.find(filter).iterator().tryNext();

    assertNotNull(actorBasic);
    assertEquals("Bruce Campbell", actorBasic.getName());
    assertEquals(127, actorBasic.getNumOfMovies());
    assertEquals(Collections.EMPTY_LIST, actorBasic.getAwards());
  }
}
