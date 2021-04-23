package com.spreetail.assignement.controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.spreetail.assignement.constants.ApplicationConstant;
import com.spreetail.assignement.constants.ApplicationMessage;
import com.spreetail.assignement.exception.DictionaryException;
import com.spreetail.assignement.service.DictionaryService;

/**
 * Entry Point of our Dictionary App
 * 
 * @author piyus
 *
 */
public class DictionaryController {

	private DictionaryService dictionaryService = new DictionaryService();

	/**
	 * Main driving method
	 * 
	 * @param s
	 */
	public static void main(String... s) {
		DictionaryController dictionaryController = new DictionaryController();
		dictionaryController.consoleMessages(ApplicationMessage.WELCOME_MSG_SHORT);

		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		do {
			String[] inputArr = input.split(" ");
			int noOfInputs = inputArr.length;
			String operationType, key, value;
			// Check for arguments
			if (noOfInputs > 0 && noOfInputs <= 3) {
				switch (noOfInputs) {
				case 1:
					operationType = inputArr[0].trim().toUpperCase();
					key = "";
					value = "";
					break;
				case 2:
					operationType = inputArr[0].trim().toUpperCase();
					key = inputArr[1].trim();
					value = "";
					break;
				default:
					operationType = inputArr[0].trim().toUpperCase();
					key = inputArr[1].trim();
					value = inputArr[2].trim();
				}
				dictionaryController.doServiceOperations(operationType, key, value);
			} else
				dictionaryController.consoleMessages(ApplicationConstant.FAULTY_INPUT);
			input = scanner.nextLine();
		} while (!input.equals("-1"));
		dictionaryController.consoleMessages(ApplicationMessage.GOODBYE_MSG_SHORT);
		scanner.close();
	}

	/**
	 * Method to print console messages
	 * 
	 * @param msg
	 */
	private void consoleMessages(String msg) {
		if (msg.equals(ApplicationMessage.WELCOME_MSG_SHORT)) {
			System.out.println(
					ApplicationMessage.DELIMITER + ApplicationMessage.WELCOME_MSG + ApplicationMessage.DELIMITER);
			System.out.println();
			System.out.println(ApplicationMessage.APP_MSG);
			System.out.println();
			System.out.println(ApplicationMessage.OPERATION_TYPE_MESSAGE);
			System.out.println();
			System.out.println(ApplicationMessage.OPERATION_MESSAGE);
			System.out.println();
			System.out.println(
					ApplicationMessage.DELIMITER + ApplicationMessage.DELIMITER + ApplicationMessage.DELIMITER);
			System.out.println();
		} else if (msg.equals(ApplicationMessage.GOODBYE_MSG_SHORT)) {
			System.out.println(ApplicationMessage.GOODBYE_MSG);
		} else if (msg.equals(ApplicationConstant.FAULTY_INPUT)) {
			System.out.println(ApplicationMessage.INVALID_INPUT_MSG);
		}

	}

	/**
	 * Method to call Dictionary service for various operations and to display
	 * output
	 * 
	 * @param operationName
	 * @param key
	 * @param value
	 */
	private void doServiceOperations(String operationName, String key, String value) {
		String result = "";
		boolean isExists = false;
		try {

			if (operationName.equals(ApplicationConstant.OPERATION_ADD)) {
				if (!key.isBlank() && !value.isBlank()) {
					result = dictionaryService.addItemByKey(key, value);
					System.out.println(result);
				} else {
					System.out.println(ApplicationMessage.INVALID_INPUT_MSG);
				}
			} else if (operationName.equals(ApplicationConstant.OPERATION_IS_KEY_EXISTS)) {
				isExists = dictionaryService.isKeyExists(key);
				if (isExists == true)
					System.out.println(isExists + ApplicationMessage.DELIMITER_MSG + ApplicationMessage.KEY_EXISTS_MSG);
				else
					System.out.println(isExists + ApplicationMessage.DELIMITER_MSG + ApplicationMessage.KEY_NOT_EXISTS_MSG);
			} else if (operationName.equals(ApplicationConstant.OPERATION_IS_VALUE_EXISTS)) {
				isExists = dictionaryService.isValueExists(key, value);
				if (isExists == true)
					System.out.println(isExists + ApplicationMessage.DELIMITER_MSG + ApplicationMessage.VALUE_EXISTS_MSG);
				else
					System.out
							.println(isExists + ApplicationMessage.DELIMITER_MSG + ApplicationMessage.VALUE_NOT_EXISTS_MSG);
			} else if (operationName.equals(ApplicationConstant.OPERATION_ALL_ITEMS)) {
				Map<String, List<String>> dictMap = dictionaryService.getItems();
				int count = 0;
				System.out.println(ApplicationMessage.ITEMS_MSG);
				for (Map.Entry<String, List<String>> map : dictMap.entrySet()) {
					List<String> valueList = map.getValue();
					if (valueList != null && !valueList.isEmpty()) {
						for (int i = 0; i < valueList.size(); i++) {
							++count;
							System.out.println(
									ApplicationMessage.DECORATOR_LEFT + count + ApplicationMessage.DECORATOR_RIGHT
											+ map.getKey() + ApplicationMessage.DELIMITER_MSG + valueList.get(i));
						}
					}
				}
			} else if (operationName.equals(ApplicationConstant.OPERATION_KEYS)) {
				List<String> keyList = dictionaryService.getKeys();
				if (keyList != null && !keyList.isEmpty()) {
					System.out.println(ApplicationMessage.KEYS_PRESENT_MSG);
					for (int count = 0; count < keyList.size(); count++) {
						System.out.println(ApplicationMessage.DECORATOR_LEFT + (count + 1)
								+ ApplicationMessage.DECORATOR_RIGHT + keyList.get(count));
					}
				}
			} else if (operationName.equals(ApplicationConstant.OPERATION_MEMBERS)) {
				if (!key.isBlank()) {
					List<String> memberList = dictionaryService.getMembersByKey(key);
					if (memberList != null && !memberList.isEmpty()) {
						System.out.println(ApplicationMessage.MEMBERS_PRESENT_FOR_KEY_MSG + key
								+ ApplicationMessage.DELIMITER_MSG);
						for (int count = 0; count < memberList.size(); count++) {
							System.out.println(ApplicationMessage.DECORATOR_LEFT + (count + 1)
									+ ApplicationMessage.DECORATOR_RIGHT + memberList.get(count));
						}
					}
				} else
					System.out.println(ApplicationMessage.INVALID_INPUT_MSG);
			} else if (operationName.equals(ApplicationConstant.OPERATION_ALL_MEMBERS)) {
				List<String> memberList = dictionaryService.getAllMembers();
				if (memberList != null && !memberList.isEmpty()) {
					System.out.println(ApplicationMessage.MEMBERS_PRESENT_MSG);
					for (int count = 0; count < memberList.size(); count++) {
						System.out.println(ApplicationMessage.DECORATOR_LEFT + (count + 1)
								+ ApplicationMessage.DECORATOR_RIGHT + memberList.get(count));
					}
				}
			} else if (operationName.equals(ApplicationConstant.OPERATION_REMOVE)) {
				if (!key.isBlank() && !value.isBlank()) {
					result = dictionaryService.remove(key, value);
					System.out.println(result);
				} else
					System.out.println(ApplicationMessage.INVALID_INPUT_MSG);
			} else if (operationName.equals(ApplicationConstant.OPERATION_REMOVEALL)) {
				if (!key.isBlank()) {
					result = dictionaryService.removeAll(key);
					System.out.println(result);
				} else
					System.out.println(ApplicationMessage.INVALID_INPUT_MSG);
			} else if (operationName.equals(ApplicationConstant.OPERATION_CLEAR)) {
				result = dictionaryService.clear();
				System.out.println(result);
			}

		} catch (DictionaryException de) {
			System.out.println(de.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
