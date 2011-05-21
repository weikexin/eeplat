package com.zephyr.erpscan.framework.exception;

public class DatastoreException extends BaseException {

	public DatastoreException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatastoreException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DatastoreException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DatastoreException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DatastoreException(String msgKey, Object[] args) {
		super(msgKey, args);
		// TODO Auto-generated constructor stub
	}
	
	public static DatastoreException datastoreError( Throwable rootCause) {
	    Object[] args = {};
	    DatastoreException datastoreException = new DatastoreException(DATASTORE_ERROR, args);
	    datastoreException.setRootCause( rootCause );
	    return (datastoreException);
	  }
	
	private static final String DATASTORE_ERROR = "DataStoreError";

}
