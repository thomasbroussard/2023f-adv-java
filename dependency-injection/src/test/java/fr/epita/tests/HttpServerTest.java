package fr.epita.tests;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.epita.di.datamodel.Patient;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.*;

public class HttpServerTest {


    @Test
    public void test() throws IOException, URISyntaxException, InterruptedException {
        InetSocketAddress address = new InetSocketAddress(10990);
        serverSetup(address);

       // clientCall();
        //task 1: GET /patients/1
        getPatient("http://localhost:10990/patients/", "1");
        //task 2: POST /patients {'name':'toto'}


    }

    private static void serverSetup(InetSocketAddress address) throws IOException {
        HttpServer server = HttpServer.create(address, 0);
        server.createContext("/patients", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "";

                switch (exchange.getRequestMethod().toUpperCase()) {
                    case "GET":

                        URI requestURI = exchange.getRequestURI();
                        String[] parts = requestURI.getPath().split("patients/");
                        //identify path params using a url pattern like patients/{id}/otherPathElements
                        String patientId =  parts[1];
                        //task1: analyze the request URI to extract the patient id
                        System.out.println(patientId);
                        // GET /patients/1
                        response = "{'id':1, 'name':'toto'}";
                        break;
                    case "POST":
                        //task 2: analyze the request body to retrieve the json
                        //string that corresponds to the patient under creation
                        exchange.getRequestBody();
                        Patient patientFromRequestBody = null;
                        System.out.println(patientFromRequestBody);
                        break;
                }
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.close();
            }
        });
        server.start();
    }

    private static void getPatient(String baseUrl, String patientId) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(baseUrl + patientId);
        URLConnection urlConnection = uri.toURL().openConnection();
        urlConnection.connect();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(urlConnection.getInputStream().readAllBytes());
        String response = new String(byteArrayInputStream.readAllBytes());
        System.out.println(uri);
        System.out.println(response);


    }


}
