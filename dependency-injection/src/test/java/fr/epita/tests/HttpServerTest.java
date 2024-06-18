package fr.epita.tests;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.*;

public class HttpServerTest {


    @Test
    public void test() throws IOException, URISyntaxException {
        InetSocketAddress address = new InetSocketAddress(10990);
        serverSetup(address);

        clientCall();

    }

    private static void serverSetup(InetSocketAddress address) throws IOException {
        HttpServer server = HttpServer.create(address, 0);
        server.createContext("/test", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "";
                switch (exchange.getRequestMethod().toUpperCase()){
                    case "GET":
                        response = "hello!";
                        break;
                    case "POST":
                        break;
                }
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.close();
            }
        });
        server.start();
    }

    private static void clientCall() throws URISyntaxException, IOException {
        URI uri = new URI("http://localhost:10990/test");
        URLConnection urlConnection = uri.toURL().openConnection();
        urlConnection.connect();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(urlConnection.getInputStream().readAllBytes());
        String response = new String(byteArrayInputStream.readAllBytes());
        System.out.println(response);
    }


}
