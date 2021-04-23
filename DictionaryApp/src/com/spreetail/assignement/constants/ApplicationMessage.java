package com.spreetail.assignement.constants;

/**
 * Class for application messages constants
 * @author piyus
 *
 */
public class ApplicationMessage {
	
	public static final String APP_MSG = "This App is a multivalue dictionary where you can store multiple keys and values";
	public static final String OPERATION_MESSAGE = "Enter Any Operation, Press -1 for Exit";
	public static final String OPERATION_TYPE_MESSAGE = "Below is the list of permissible operations \n \n "
			+ "1)ADD - To add members in dictionary \n "
			+ "2)REMOVE - Remove a value from given key \n "
			+ "3)REMOVEALL - Remove all values of a key \n "
			+ "4)KEYS - Get all keys of dictionary \n "
			+ "5)CLEAR- Empty the dictionary \n "
			+ "6)ITEMS - Get all key and values from dictionary \n "
			+ "7)ALLMEMBERS - Get all values of dictionary \n "
			+ "8)MEMBERS- Get all values of a key \n "
			+ "9)KEYEXISTS - Check for key existance \n "
			+ "10)VALUEEXISTS - Check for value existance";
	public static final String ADD_SUCCESS_MSG = "Added";
	public static final String REMOVE_SUCCESS_MSG = "Removed";
	public static final String CLEAR_SUCCESS_MSG = "Cleared";
	public static final String KEY_EXISTS_MSG = " Your key exits ! Bingo ! ";
	public static final String KEY_NOT_EXISTS_MSG = " Sorry Your key doesn't exits ";
	public static final String VALUE_EXISTS_MSG = " Your value exists ! Bingo ! ";
	public static final String VALUE_NOT_EXISTS_MSG = " Sorry Your value doesn't exits ";
	public static final String MEMBERS_PRESENT_MSG = "All members present in dictionary are :-";
	public static final String KEYS_PRESENT_MSG = "Keys present in dictionary are :-";
	public static final String MEMBERS_PRESENT_FOR_KEY_MSG = "Members present in dictionary for ";
	public static final String ITEMS_MSG = "Items of dictionary are :-";
	public static final String DECORATOR_LEFT = "(";
	public static final String DECORATOR_RIGHT = ")";
	public static final String DELIMITER_MSG = " : ";
	public static final String WELCOME_MSG = "****WELCOME TO SPREETAIL****";
	public static final String WELCOME_MSG_SHORT = "WEL";
	
	public static final String GOODBYE_MSG_SHORT = "BYE";
	public static final String GOODBYE_MSG = "Goodbye ! See you soon ...";
	public static final String DELIMITER = "******************************";
	public static final String INVALID_INPUT_MSG = "Invalid Input, check input specifications";
}
