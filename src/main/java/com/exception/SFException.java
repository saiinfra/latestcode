package com.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author SFException is Exception Handling Class
 *
 */
public class SFException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static SFException wrap(Throwable exception, ErrorCode errorCode) {
		if (exception instanceof SFException) {
			SFException se = (SFException) exception;
			if (errorCode != null && errorCode != se.getErrorCode()) {
				return new SFException(exception.getMessage(), exception,
						errorCode);
			}
			return se;
		} else {
			return new SFException(exception.getMessage(), exception, errorCode);
		}
	}

	public static SFException wrap(Throwable exception) {
		return wrap(exception, null);
	}

	private ErrorCode errorCode;
	private final Map<String, Object> properties = new TreeMap<String, Object>();

	/**
	 * 
	 * @param errorCode
	 */
	public SFException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * @param message
	 * @param errorCode
	 */
	public SFException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * @param cause
	 * @param errorCode
	 */
	public SFException(Throwable cause, ErrorCode errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param errorCode
	 */
	public SFException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * @return errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * 
	 * @param Setting
	 *            errorCode
	 * @return SFException
	 */
	public SFException setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	/**
	 * 
	 * @return properties
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T) properties.get(name);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return SFException
	 */
	public SFException set(String name, Object value) {
		properties.put(name, value);
		return this;
	}

	public void printStackTrace(PrintStream s) {
		synchronized (s) {
			printStackTrace(new PrintWriter(s));
		}
	}

	public void printStackTrace(PrintWriter s) {
		synchronized (s) {
			s.println(this);
			s.println("\t-------------------------------");
			if (errorCode != null) {
				s.println("\t" + errorCode + ":"
						+ errorCode.getClass().getName());
			}
			for (String key : properties.keySet()) {
				s.println("\t" + key + "=[" + properties.get(key) + "]");
			}
			s.println("\t-------------------------------");
			StackTraceElement[] trace = getStackTrace();
			for (int i = 0; i < trace.length; i++)
				s.println("\tat " + trace[i]);

			Throwable ourCause = getCause();
			if (ourCause != null) {
				ourCause.printStackTrace(s);
			}
			s.flush();
		}
	}

}
