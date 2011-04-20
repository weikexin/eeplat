/**
 * 
 */
package com.zephyr.erpscan.framework.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author t
 *
 */
public class BaseException extends Exception {

	/**
	 * 
	 */
	public BaseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public BaseException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BaseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	/**
	   *  Print both the normal and rootCause stack traces.
	   */
	  public void printStackTrace(PrintWriter writer) {
	    super.printStackTrace(writer);
	    if(getRootCause() != null) {
	      getRootCause().printStackTrace(writer);
	    }
	    writer.flush();
	  }

	  /**
	   *  Print both the normal and rootCause stack traces.
	   */
	  public void printStackTrace(PrintStream outStream) {
	    printStackTrace(new PrintWriter(outStream));
	  }

	  /**
	   *  Print both the normal and rootCause stack traces.
	   */
	  public void printStackTrace() {
	    printStackTrace(System.err);
	  }

	  /**
	   *  Return the root cause exception, if one exists.
	   */
	  public Throwable getRootCause() {
	    return rootCause;
	  }

	  /**
	   *  Retrieve the collection of exceptions.
	   */
	  public List getExceptions() {
	    return exceptions;
	  }

	  /**
	   *  A Constructor that takes a root cause throwable.
	   */
	  public BaseException(Throwable cause) {
	    this.rootCause = rootCause;
	  }

	  /**
	   * Retrieve the optional arguments.
	   */
	  public Object[] getMessageArgs() {
	    return messageArgs;
	  }
	  public BaseException(String msgKey,
	                       Object[] args) {
	    this.messageKey = msgKey;
	    this.messageArgs = args;
	  }

	  /**
	   *  Set a nested, encapsulated exception to provide more low-level detailed
	   *  information to the client.
	   */
	  public void setRootCause(Throwable anException) {
	    rootCause = anException;
	  }

	  /**
	   * Add an exception to the exception collection.
	   */
	  public void addException(BaseException ex) {
	    exceptions.add(ex);
	  }

	  /**
	   * Set the key to the bundle.
	   */
	  public void setMessageKey(String key) {
	    this.messageKey = key;
	  }

	  /**
	   * Retrieve the message bundle key.
	   */
	  public String getMessageKey() {
	    return messageKey;
	  }

	  /**
	   * Set an object array that can be used for parametric replacement.
	   */
	  public void setMessageArgs(Object[] args) {
	    this.messageArgs = args;
	  }

	  protected Throwable rootCause = null;

	  private List exceptions = new ArrayList();

	  /**
	   * An optional array of arguments to use with the bundle message.
	   */
	  private Object[] messageArgs = null;

	  /**
	   * The key to the bundle message.
	   */
	  private String messageKey = null;

}
