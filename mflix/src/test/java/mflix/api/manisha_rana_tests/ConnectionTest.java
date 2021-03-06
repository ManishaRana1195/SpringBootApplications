package mflix.api.manisha_rana_tests;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ConnectionTest {

  @Value("${spring.mongodb.uri}")
  private String dbUri;

  private MongoClient mongoClient;

  @Before
  public void setUp() {
    /*check later*/
  }

  @Test
  public void createMongoClientAndGetMflixDatabase() {
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString(dbUri))
        .applyToConnectionPoolSettings(builder -> builder.maxWaitTime(1000, TimeUnit.MILLISECONDS))
        .build();

    MongoClient client = MongoClients.create(settings);

    assertNotNull(client);
    assertNotNull(client.getDatabase("mflix"));
    assertEquals(45993, client.getDatabase("mflix").getCollection("movies").count());
  }

  @Test
  public void testToFindSalmaHayekMovies() {
    mongoClient = MongoClients.create(dbUri);
    MongoCollection<Document> movieCollection = mongoClient
        .getDatabase("mflix")
        .getCollection("movies");

    Document document = new Document("cast", "Salma Hayek");
    Document result = movieCollection.find(document).limit(1).iterator().tryNext();

    assertNotNull(result);
    assertEquals("Roadracers", result.get("title"));
    assertEquals(1994, result.get("year"));

    Bson bsonFilter = eq("cast", "Salma Hayek");
    Document resultWithBson = movieCollection.find(bsonFilter).limit(1).iterator().tryNext();

    assertEquals(result, resultWithBson);
  }

  @Test
  public void testToFindAllElementsInArray() {
    Document document = new Document("cast", new Document("$all", Arrays.asList("Salma Hayek", "Johny Depp")));
    Document result = getResult(document).iterator().tryNext();

    Bson bsonFilter = all("cast", "Salma Hayek", "Johny Depp");
    Document resultWithBson = getResult(bsonFilter).iterator().tryNext();

    assertEquals(result, resultWithBson);
  }

  private FindIterable<Document> getResult(Bson filter) {
    mongoClient = MongoClients.create(dbUri);
    MongoCollection<Document> movieCollection = mongoClient
        .getDatabase("mflix")
        .getCollection("movies");

    return movieCollection.find(filter);
  }


  @Test
  public void testWithMultipleFilters() {
    Bson bsonFilter = and(eq("cast", "Tom Hanks"),
        gte("year", 1990),
        lt("year", 2005),
        gte("metacritic", 85));

    List<Document> results = new ArrayList<>();
    getResult(bsonFilter).into(results);
    assertEquals(3, results.size());
  }

  @Test
  public void testGettingSelectedFieldsUsingProjections() {
    Document filter = new Document("cast", "Salma Hayek");
    Document result = getResult(filter)
        .limit(1)
        .projection(new Document("year", 1).append("title", 1))
        .iterator()
        .tryNext();

    assertNotNull(result);
    assertEquals(3, result.keySet().size());
    assertTrue(result.keySet().containsAll(Arrays.asList("_id", "year", "title")));

    Document resultWithShortProjection = getResult(eq("cast", "Salma Hayek"))
        .limit(1)
        .projection(fields(include("year", "title")))
        .iterator()
        .tryNext();

    assertEquals(result, resultWithShortProjection);
  }

  @Test(expected = NoSuchElementException.class)
  public void shouldThrowNoSuchElementException() {
    Bson filter = eq("cast", "blaahBoohBojha");
    Document next = getResult(filter).iterator().next();
  }


}
