package mflix.api.manisha_rana_tests;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import mflix.api.daos.TicketTest;
import org.apache.catalina.Pipeline;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableAutoConfiguration
@EnableConfigurationProperties
@RunWith(SpringJUnit4ClassRunner.class)
public class AggregationTest extends TicketTest {

  private MongoCollection<Document> moviesCollection;

  @Before
  public void setup() throws IOException {
    String dbUri = getProperty("spring.mongodb.uri");
    MongoDatabase mongoClient = MongoClients.create(dbUri).getDatabase("mflix");
    moviesCollection = mongoClient.getCollection("movies");
  }

  @Test
  public void testBasicAggregationQuery() {
    Bson filter = eq("countries", "Portugal");
    Bson matchStage = Aggregates.match(filter);
    List<Bson> pipeline = new ArrayList<>();
    pipeline.add(matchStage);

    AggregateIterable<Document> aggregateIterable = moviesCollection.aggregate(pipeline);

    ArrayList<Document> filteredDocuments = new ArrayList<>();
    aggregateIterable.into(filteredDocuments);

    assertEquals(152, filteredDocuments.size());
  }

  @Test
  public void testMultiStageAggregationQuery() {

    ArrayList<Bson> pipeline = new ArrayList<>();

    
    Bson matchFilter = Aggregates.match(eq("countries", "Portugal"));
    pipeline.add(matchFilter);

    AggregateIterable<Document> aggregateIterable = moviesCollection.aggregate(pipeline);

    ArrayList<Document> filteredDocuments = new ArrayList<>();
    aggregateIterable.into(filteredDocuments);


  }
}
