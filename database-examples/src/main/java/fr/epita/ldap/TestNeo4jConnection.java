package fr.epita.ldap;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.util.Map;

import static org.neo4j.driver.Values.parameters;


public class TestNeo4jConnection {

    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("neo4j", "admin"));
        Session session = driver.session();
        Transaction tx = session.beginTransaction();

        displaySearch(tx, "Tom Hanks");
        createNode(tx, "Jean Dujardin");
        displaySearch(tx, "Jean Dujardin");

        Result result = tx.run("MATCH (Person {name: 'Tom Hanks'})--(Movie) RETURN Movie.title");
        while(result.hasNext()) {
            System.out.println(result.next().get("Movie.title").asString());
        }

        tx.commit();
        tx.close();
        session.close();
        driver.close();
    }

    private static void displaySearch(Transaction tx, String name) {
        Result result = tx.run("MATCH (n {name: $varName }) RETURN n", parameters("varName", name));

        while (result.hasNext()) {
            Record row = result.next();
            Value value = row.get("n");
            Map<String, Object> properties = value.asEntity().asMap();
            System.out.println(properties);
            System.out.println(String.valueOf(properties.get("name")));
        }
    }


    private static void createNode(Transaction tx, String name) {
        tx.run("CREATE (n:Person {name: $varName }) ", parameters("varName", name));
    }
}
