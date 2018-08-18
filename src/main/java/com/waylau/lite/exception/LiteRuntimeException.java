/**
 * Welcome to https://waylau.com
 */
package com.waylau.lite.exception;

/**
 * Lite LiteRuntimeException.
 * 
 * @since 1.0.0 2018年8月18日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public class LiteRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LiteRuntimeException() {
		super();
	}

	public LiteRuntimeException(String message) {
		super(message);
	}

	public LiteRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public LiteRuntimeException(Throwable cause) {
		super(cause);
	}

	protected LiteRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
