/**
 * Welcome to https://waylau.com
 */
package com.waylau.lite;

/**
 * Lite Server.
 * 
 * @since 1.0.0 2018年8月18日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public interface LiteServer {

	/**
	 * Default port is 8080
	 */
	void run();

	void run(int port);
	
	void run(String[] args);

}
