/**
 * 
 */
package com.zephyr.erpscan.object;

import java.io.Serializable;

/**
 * @author t
 *
 */
public class HtmlSelectOption implements Serializable {

	private String id;
	private String displayName;

	public HtmlSelectOption() { }

	/**
	* Constructor for the HtmlSelectOption object
	*
	*@param id Description of the Parameter
	*@param displayName Description of the Parameter
	*/
	public HtmlSelectOption(String id, String displayName) {
	this.id = id;
	this.displayName = displayName;
	}

	public String getDisplayName() {
	return displayName;
	}

	public void setDisplayName(String displayName) {
	this.displayName = displayName;
	}

	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

}
