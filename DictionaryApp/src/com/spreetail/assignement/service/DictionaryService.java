package com.spreetail.assignement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spreetail.assignement.constants.ApplicationMessage;
import com.spreetail.assignement.constants.ExceptionConstant;
import com.spreetail.assignement.exception.DictionaryException;
import com.spreetail.assignement.model.Dictionary;

public class DictionaryService {

	private Dictionary dictionary = null;

	public DictionaryService() {
		dictionary = new Dictionary();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		dictionary.setMultiValueDict(map);
	}

	/**
	 * Method to get dictionary
	 * 
	 * @return
	 */
	private Map<String, List<String>> getDictionary() {
		Map<String, List<String>> dictMap = dictionary.getMultiValueDict();
		return dictMap;
	}

	/**
	 * Method to add a member for a given key
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws DictionaryException
	 */
	public String addItemByKey(String key, String value) throws DictionaryException {
		System.out.println("Going to add value : " + value + " for Key : " + key);
		Map<String, List<String>> dictMap = getDictionary();
		if (dictMap != null && dictMap.isEmpty() != true) {
			// Check if key exists and add the item
			if (dictMap.containsKey(key)) {
				List<String> membersList = dictMap.get(key);
				if (membersList.contains(value)) {
					//Already existing value
					throw new DictionaryException(ExceptionConstant.ADD_ERROR);
				} else {
					membersList.add(value);
					dictMap.put(key, membersList);
					dictionary.setMultiValueDict(dictMap);
				}
				// Code for a fresh key and value insert
			} else {
				List<String> valueList = new ArrayList<>();
				valueList.add(value);
				dictMap.put(key, valueList);
			}
			// First time entry of items in map
		} else {
			List<String> valueList = new ArrayList<>();
			valueList.add(value);
			dictMap.put(key, valueList);

		}
		return ApplicationMessage.ADD_SUCCESS_MSG;
	}

	/**
	 * Method to get all keys of dictionary
	 * 
	 * @return
	 * @throws DictionaryException
	 */
	public List<String> getKeys() throws DictionaryException {
		System.out.println("Going to fetch all keys of dictionary : ");
		Map<String, List<String>> dictMap = getDictionary();
		List<String> resultKeys = null;
		if (dictMap != null && dictMap.isEmpty() != true) {
			resultKeys = new ArrayList<String>();
			for (Map.Entry<String, List<String>> map : dictMap.entrySet()) {
				resultKeys.add(map.getKey());
			}
		} else
			throw new DictionaryException(ExceptionConstant.DICTIONARY_EMPTY_KEYS);
		return resultKeys;
	}

	/**
	 * Method to Get All members of a given key
	 * 
	 * @param key
	 * @return
	 * @throws DictionaryException
	 */
	public List<String> getMembersByKey(String key) throws DictionaryException {
		Map<String, List<String>> dictMap = getDictionary();
		List<String> membersList = null;
		if (dictMap != null && dictMap.isEmpty() != true) {
			if (dictMap.containsKey(key))
				membersList = dictMap.get(key);
			else
				throw new DictionaryException(ExceptionConstant.KEY_EXIST_ERROR);
		}
		return membersList;

	}

	/**
	 * Method to get all values and keys from dictionary
	 * 
	 * @return
	 * @throws DictionaryException
	 */
	public Map<String, List<String>> getItems() throws DictionaryException {
		System.out.println("Going to fetch all items of dictionary : ");
		Map<String, List<String>> dictMap = getDictionary();
		if (dictMap != null && dictMap.isEmpty() != true)
			return dictMap;
		else
			throw new DictionaryException(ExceptionConstant.DICTIONARY_EMPTY_KEYS);
	}

	/**
	 * Method to return all values from the dictionary
	 * 
	 * @return
	 */
	public List<String> getAllMembers() {
		Map<String, List<String>> dictMap = getDictionary();
		List<String> resultValues = null;
		if (dictMap != null && dictMap.isEmpty() != true) {
			resultValues = new ArrayList<String>();
			for (Map.Entry<String, List<String>> map : dictMap.entrySet()) {
				List<String> valueList = map.getValue();
				resultValues.addAll(valueList);
			}
		}
		return resultValues;
	}

	/**
	 * Method to remove a value for a given key
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws DictionaryException
	 */
	public String remove(String key, String value) throws DictionaryException {
		System.out.println("Going to remove value : " + value + " for Key : " + key);
		Map<String, List<String>> dictMap = getDictionary();
		if (dictMap != null && dictMap.isEmpty() != true) {
			if (dictMap.containsKey(key)) {
				List<String> membersList = dictMap.get(key);
				if (membersList.contains(value)) {
					membersList.remove(value);
					if (membersList != null && membersList.isEmpty())
						dictMap.remove(key);
				} else {
					throw new DictionaryException(ExceptionConstant.VALUE_EXIST_ERROR);
				}
			} else
				throw new DictionaryException(ExceptionConstant.KEY_EXIST_ERROR);
		}
		return ApplicationMessage.REMOVE_SUCCESS_MSG;

	}

	/**
	 * Method to remove a key and its all values
	 * 
	 * @param key
	 * @return
	 * @throws DictionaryException
	 */
	public String removeAll(String key) throws DictionaryException {
		System.out.println("Going to remove key : " + key + " and its all values");
		Map<String, List<String>> dictMap = getDictionary();
		if (dictMap != null && dictMap.isEmpty() != true) {
			if (dictMap.containsKey(key)) {
				dictMap.remove(key);
			} else {
				throw new DictionaryException(ExceptionConstant.KEY_EXIST_ERROR);
			}
		}
		return ApplicationMessage.REMOVE_SUCCESS_MSG;
	}

	/**
	 * Method to remove all keys and values
	 * 
	 * @return
	 * @throws DictionaryException
	 */
	public String clear() throws DictionaryException {
		System.out.println("Going to empty the dictionary");
		Map<String, List<String>> dictMap = getDictionary();
		if (dictMap != null && dictMap.isEmpty() != true) {
			dictMap.clear();
		} else
			throw new DictionaryException(ExceptionConstant.KEY_EXIST_ERROR);
		return ApplicationMessage.CLEAR_SUCCESS_MSG;
	}

	/**
	 * Method to check whether a key exists
	 * 
	 * @return
	 */
	public boolean isKeyExists(String key) {
		System.out.println("Checking for key : " + key + " existance");
		boolean keyExist = false;
		Map<String, List<String>> dictMap = getDictionary();
		if (dictMap != null && dictMap.isEmpty() != true) {
			if (dictMap.containsKey(key))
				keyExist = true;
		}
		return keyExist;
	}

	/**
	 * Method to check whether a particular value exists for a given key
	 * 
	 * @return
	 */
	public boolean isValueExists(String key, String value) {
		System.out.println("Checking for value : " + value + " existance");
		boolean valueExist = false;
		Map<String, List<String>> dictMap = getDictionary();
		if (dictMap != null && dictMap.isEmpty() != true) {
			if (dictMap.containsKey(key)) {
				List<String> membersList = dictMap.get(key);
				if (membersList.contains(value))
					valueExist = true;
			}
		}
		return valueExist;
	}
}
