package com.github.stefanbirkner.serverrule;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;

public class JettyTest {
	@Rule
	public final Jetty jetty = new Jetty();

	@Test
	public void povidesIndexJsp() throws Exception {
		URL url = new URL("http://localhost:8080/");
		InputStream is = url.openStream();
		String pageSource = IOUtils.toString(is);
		assertEquals("test successful", pageSource);
	}
}
