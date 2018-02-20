package com.csquare.framework.validation;

import javax.xml.bind.ValidationException;

public class CSquareInputDataValidator {

	
	public static boolean isValidEmailAddress(String email) throws ValidationException {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if(!m.matches()){
        	throw new ValidationException("Invalid email");
        }
        return m.matches();
	}

	
	public static boolean validatePhoneNumber(String phoneNo) throws ValidationException {
		boolean isValid=false;
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}")){
			isValid= true;
		} 
		//validating phone number with -, . or spaces
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
			isValid=true;
		}
		//validating phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")){
			isValid=true;
		}
		//validating phone number where area code is in braces ()
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")){
			isValid=true;
		}
		
		if(!isValid){
			throw new ValidationException("Invalid Phone");
		}
		//return false if nothing matches the input
		 return isValid;
		
	}
	
}
