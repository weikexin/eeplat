package org.json;

/**
 * The JSONException is thrown by the JSON.org classes then things are amiss.
 * @author  JSON.org
 * @version  2008-09-18
 */
public class JSONException extends Exception {
    /**
	 * @uml.property  name="cause"
	 */
    private Throwable cause;

    /**
     * Constructs a JSONException with an explanatory message.
     * @param message Detail about the reason for the exception.
     */
    public JSONException(String message) {
        super(message);
    }

    public JSONException(Throwable t) {
        super(t.getMessage());
        this.cause = t;
    }

    /**
	 * @return
	 * @uml.property  name="cause"
	 */
    public Throwable getCause() {
        return this.cause;
    }
}
