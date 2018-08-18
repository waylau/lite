/**
 * Welcome to https://waylau.com
 */
package com.waylau.lite.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.waylau.lite.AppConfig;
import com.waylau.lite.LiteServer;
import com.waylau.lite.exception.LiteRuntimeException;
import com.waylau.lite.util.CommandLineArgs;
import com.waylau.lite.util.CommandLineArgsParser;

/**
 * Lite Jetty Server.
 * 
 * @since 1.0.0 2018年8月18日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public class LiteJettyServer implements LiteServer {

	static final Logger logger = LoggerFactory.getLogger(LiteJettyServer.class);

	private static final String CONTEXT_PATH = "/";
	private static final String MAPPING_URL = "/*";
	private static final int PORT = 8080;
	private static final String PORT_NAME = "port";
	private int port;

	@Override
	public void run() {
		this.port = PORT;
		this.run(this.port);
	}

	@Override
	public void run(String[] args) {
		CommandLineArgs commandLineArgs = CommandLineArgsParser.parse(args);
		this.port = commandLineArgs.getIntArg(PORT_NAME);
		this.run(port);
	}

	@Override
	public void run(int port) {

		Server server = new Server(port);
		server.setHandler(this.servletContextHandler(this.webApplicationContext()));

		try {
			server.start();
		} catch (Exception e) {
			logger.error("Lite start exception!", e);
			throw new LiteRuntimeException("Lite started exception!", e);
		}

		logger.info("SSM start at {}", this.port);

		try {
			server.join();
		} catch (InterruptedException e) {
			logger.error("Lite join exception!", e);
			throw new LiteRuntimeException("Lite join exception!", e);
		}

	}

	private ServletContextHandler servletContextHandler(WebApplicationContext context) {
		ServletContextHandler handler = new ServletContextHandler();
		handler.setContextPath(CONTEXT_PATH);
		handler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
		handler.addEventListener(new ContextLoaderListener(context));
		return handler;
	}

	private WebApplicationContext webApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		return context;
	}

}
