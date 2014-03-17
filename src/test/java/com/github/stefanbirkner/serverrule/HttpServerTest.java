package com.github.stefanbirkner.serverrule;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class HttpServerTest {
    @Rule
    public final HttpServer server = new HttpServer();

    @Test
    public void povidesIndexJsp() throws Exception {
        URL url = new URL("http://localhost:8080/");
        InputStream is = url.openStream();
        String pageSource = IOUtils.toString(is);
        assertEquals("test successful", pageSource);
    }
}
