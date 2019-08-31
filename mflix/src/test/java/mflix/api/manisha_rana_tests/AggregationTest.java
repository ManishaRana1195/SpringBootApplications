package mflix.api.manisha_rana_tests;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
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
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    Bson unwind = Aggregates.unwind("$cast");
    Bson matchFilter = Aggregates.match(eq("countries", "Portugal"));
    Bson groupAggregate = Aggregates.group("$cast", Accumulators.sum("count", 1));
    Bson sortOrder = Aggregates.sort(Sorts.descending("count"));

    ArrayList<Bson> pipeline = new ArrayList<>();
    pipeline.add(matchFilter);
    pipeline.add(unwind);
    pipeline.add(groupAggregate);
    pipeline.add(sortOrder);

    ArrayList<Bson> shorterPipeline = new ArrayList<>();
    shorterPipeline.add(matchFilter);
    shorterPipeline.add(unwind);
    shorterPipeline.add(Aggregates.sortByCount("$cast"));

    AggregateIterable<Document> aggregateIterable = moviesCollection.aggregate(pipeline);
    AggregateIterable<Document> aggregateByShorter = moviesCollection.aggregate(shorterPipeline);


    ArrayList<Document> filteredDocuments = new ArrayList<>();
    ArrayList<Document> filteredByShorter = new ArrayList<>();
    aggregateIterable.into(filteredDocuments);
    aggregateByShorter.into(filteredByShorter);

    assertTrue(!filteredDocuments.isEmpty());
    assertEquals(filteredByShorter, filteredDocuments);
  }

  @Test
  public void testComplexAggregationQuery() {
    ArrayList<Bson> pipeline = new ArrayList<>();
    Bson matchFilter = Aggregates.match(eq("countries", "Portugal"));
    Bson unwindCast = Aggregates.unwind("$cast");
    Bson groupByCast = Aggregates.group("", Accumulators.addToSet("cast_list", "$cast"));
    Facet castMembersFacet = new Facet("cast_members", unwindCast, groupByCast);


    Bson unwindGenres = Aggregates.unwind("$genres");
    Bson genresCount = Aggregates.sortByCount("$genres");
    Facet genresCountFacet = new Facet("genres_count", unwindGenres, genresCount);

    Bson yearBucket = Aggregates.bucketAuto("$year", 10);
    Facet yearBucketFacet = new Facet("year_bucket", yearBucket);

    Bson facets = Aggregates.facet(castMembersFacet, genresCountFacet, yearBucketFacet);

    pipeline.add(matchFilter);
    pipeline.add(facets);

    AggregateIterable<Document> aggregateIterable = moviesCollection.aggregate(pipeline);
    ArrayList<Document> filteredDocuments = new ArrayList<>();
    aggregateIterable.into(filteredDocuments);

    assertFalse(filteredDocuments.isEmpty());
    assertEquals(1, filteredDocuments.size());
  }
}
