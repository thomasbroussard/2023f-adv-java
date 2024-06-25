package fr.epita.eventure.tests;

import com.mongodb.client.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.bson.Document;;
import org.bson.conversions.Bson;
import java.util.concurrent.TimeUnit;
import org.bson.Document;

public class TestMongoDBAccess {

    MongoCollection<Document> events;

    @BeforeEach
    public void setup(){
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("eventure");
        events = database.getCollection("events");
    }


    @Test
    public void mongodbAccess(){
        /*{
  "_id": {
    "$oid": "667a9eb2ac3c66a9b867dbb2"
  },
  "title": "test",
  "description": "first event",
  "location": "somewhere"
}*/
        createDocument("test from java", "brand new event", "603");


    }

    public void createDocument(String title, String description, String location) {
        Document document = new Document("title", title)
                .append("description", description)
                .append("location", location);
        events.insertOne(document);
        System.out.println("Document inserted with ID: " + document.getObjectId("_id"));
    }

}
