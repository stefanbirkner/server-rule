package com.github.stefanbirkner.serverrule;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class HttpServerTest {
    private static final int ARBITRARY_PORT = 9345;

    @Rule
    public final HttpServer server = new HttpServer();

    @Rule
    public final HttpServer serverOnArbitraryPort = new HttpServer().withPort(ARBITRARY_PORT);

    @Test
    public void providesIndexJspOnDefaultPort8080() throws Exception {
        URL url = new URL("http://localhost:8080/");
        InputStream is = url.openStream();
        String pageSource = IOUtils.toString(is);
        assertEquals("test successful", pageSource);
    }

    @Test
    public void providesIndexJspOnSpecifiedPort() throws Exception {
        URL url = new URL("http://localhost:" + ARBITRARY_PORT + "/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        assertEquals(200, connection.getResponseCode());
    }
}
