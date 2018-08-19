/**
 * Welcome to https://waylau.com
 */
package com.waylau.lite;

/**
 * Lite infomation.
 * 
 * @since 1.0.0 2018年8月19日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public class Lite {

	private String author;
	private String version;
	
	public Lite(String author, String version) {
		this.author = author;
		this.version = version;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
