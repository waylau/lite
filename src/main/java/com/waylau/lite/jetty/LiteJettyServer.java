/**
 * Welcome to https://waylau.com
 */
package com.waylau.lite.jetty;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.waylau.lite.LiteConfig;
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
	private static final String PORT_NAME = "port";
	private static final int PORT = 8080;
	private Class<?> annotatedClass;
	
	public LiteJettyServer() {
	}
	
	public LiteJettyServer(Class<?> annotatedClass) {
		this.annotatedClass = annotatedClass;
	}

	@Override
	public void run() {
		this.run(PORT);
	}

	@Override
	public void run(String[] args) {
		CommandLineArgs commandLineArgs = CommandLineArgsParser.parse(args);
		Integer port = commandLineArgs.getIntArg(PORT_NAME);
		
		// 判断是否通过命令行传port
		if (port == null) {
			this.run();
		} else {
			this.run(port);
		}

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

		logger.info("Lite start at {}", port);

		try {
			server.join();
		} catch (InterruptedException e) {
			logger.error("Lite join exception!", e);
			throw new LiteRuntimeException("Lite join exception!", e);
		}

	}

    private ServletContextHandler servletContextHandler(WebApplicationContext ct) {
    	// 启用Session管理器
        ServletContextHandler handler = 
        		new ServletContextHandler(ServletContextHandler.SESSIONS);
        
        handler.setContextPath(CONTEXT_PATH);
        handler.addServlet(new ServletHolder(new DispatcherServlet(ct)), 
        		MAPPING_URL);
        handler.addEventListener(new ContextLoaderListener(ct));
        
        // 添加Spring Security过滤器
        FilterHolder filterHolder=new FilterHolder(DelegatingFilterProxy.class);
        filterHolder.setName("springSecurityFilterChain"); 
        handler.addFilter(filterHolder, MAPPING_URL, 
        		EnumSet.of(DispatcherType.REQUEST));
        
        return handler;
    }

	private WebApplicationContext webApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(LiteConfig.class);
		
		if (this.annotatedClass != null) {
			context.register(this.annotatedClass); // 支持外部上下文
		}

		return context;
	}

}
