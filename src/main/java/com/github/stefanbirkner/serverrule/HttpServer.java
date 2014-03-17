package com.github.stefanbirkner.serverrule;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.rules.ExternalResource;

/**
 * The {@code HttpServer} rule starts and stops an HTTP server at port 8080. The
 * webapp's directory is {@code src/test/webapp}.
 * <p/>
 * The test below checks the page source of a page provided by HttpServer.
 * <pre>
 * public class HttpServerTest {
 * 	&#064;Rule
 * 	public final HttpServer server = new HttpServer();
 *
 * 	&#064;Test
 * 	public void povidesIndexJsp() throws Exception {
 * 		URL url = new URL(&quot;http://localhost:8080/&quot;);
 * 		InputStream is = url.openStream();
 * 		String pageSource = IOUtils.toString(is);
 * 		assertEquals(&quot;test successful&quot;, pageSource);
 *    }
 * }
 * </pre>
 * <p/>
 * <p/>
 * You can create an HTTP server at a different port, too.
 * <pre>
 * 	&#064;Rule
 * 	public final HttpServer server = new HttpServer().withPort(1234);
 * </pre>
 */
public class HttpServer extends ExternalResource {
    private static final int DEFAULT_PORT = 8080;
    private final int port;
    private Server server;

    public HttpServer() {
        this(DEFAULT_PORT);
    }

    public HttpServer(int port) {
        this.port = port;
    }

    public HttpServer withPort(int port) {
        return new HttpServer(port);
    }

    @Override
    protected void before() throws Throwable {
        server = new Server(port);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setResourceBase("src/test/webapp");
        webapp.setParentLoaderPriority(true);

        server.setHandler(webapp);
        server.start();
    }

    @Override
    protected void after() {
        try {
            server.stop();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
