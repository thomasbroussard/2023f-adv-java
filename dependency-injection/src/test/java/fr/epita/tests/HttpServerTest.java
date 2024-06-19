package fr.epita.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.epita.di.conf.ApplicationConfiguration;
import fr.epita.di.datamodel.Patient;
import fr.epita.di.services.impl.DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.support.ObjectNameManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.io.*;
import java.net.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@Transactional
@Commit
public class HttpServerTest {


    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    //task3 make this autowired works correctly
    @Autowired
    DataService dataService;


    @Test
    public void test() throws IOException, URISyntaxException, InterruptedException {
        InetSocketAddress address = new InetSocketAddress(10990);
        serverSetup(address);

        // clientCall();
        //task 1: GET /patients/1
        getPatient("http://localhost:10990/patients/", "1");
        //task 2: POST /patients {'name':'toto'}

        postPatient("http://localhost:10990/patients/", "{'name':'toto'}");


    }

    private void serverSetup(InetSocketAddress address) throws IOException {
        HttpServer server = HttpServer.create(address, 0);
        server.createContext("/patients", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "";
                int responseCode = 200;

                try {
                    switch (exchange.getRequestMethod().toUpperCase()) {
                        case "GET":

                            URI requestURI = exchange.getRequestURI();
                            String[] parts = requestURI.getPath().split("patients/");
                            //identify path params using a url pattern like patients/{id}/otherPathElements
                            String patientId = parts[1];
                            //task1: analyze the request URI to extract the patient id
                            System.out.println(patientId);
                            // GET /patients/1
                            response = "{'id':1, 'name':'toto'}";
                           // dataService.getPatient(Integer.parseInt(patientId));
                            response = OBJECT_MAPPER.writeValueAsString(dataService.getPatient(Integer.parseInt(patientId)));
                            break;
                        case "POST":
                            //task 2: analyze the request body to retrieve the json
                            //string that corresponds to the patient under creation
                            String patientFromRequestBody = new String(exchange.getRequestBody().readAllBytes());
                            System.out.println(patientFromRequestBody);

                            //task 4: use jackson to convert the raw string to a patient instance
                            Patient patient = OBJECT_MAPPER.readValue(patientFromRequestBody, Patient.class);
                            //dataService.createPatient(patient);
                            exchange.getResponseHeaders().add("Location", "patients/" + patient.getId());
                            responseCode = 201;
                            break;

                    }
                } finally {
                    exchange.sendResponseHeaders(responseCode, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.close();
                }

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

    private static void postPatient(String baseUrl, String patientData) throws URISyntaxException, IOException {
        URI uri = new URI(baseUrl);
        URL url = uri.toURL();
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);

        try (OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = patientData.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // print result
                System.out.println(response.toString());
            }
        } else {
            System.out.println("POST request not worked");
        }
    }


}
