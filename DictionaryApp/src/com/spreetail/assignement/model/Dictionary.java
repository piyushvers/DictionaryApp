package com.spreetail.assignement.model;

import java.util.List;
import java.util.Map;

/**
 * Dictionary Model that would be storing the actual data
 * 
 * @author piyus
 *
 */
public class Dictionary {

	// Map to hold key and multi-value pairs
	private Map<String, List<String>> multiValueDict;

	public Map<String, List<String>> getMultiValueDict() {
		return multiValueDict;
	}

	public void setMultiValueDict(Map<String, List<String>> multiValueDict) {
		this.multiValueDict = multiValueDict;
	}

}
