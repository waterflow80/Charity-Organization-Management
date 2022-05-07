package utils;

/**
 * This class is responsible for checking whether the user's input
 * is in the appropriate format or no*/
public class InputChecker {
	
	/**
	 * Check whether the given email is valid or not*/
	public boolean isValidEmail(String email) {
		String regex = "^[a-zA-Z]{3,26}@(gmail|outlook|hotmail|yahoo)\\.[a-zA-Z]{2,6}$";
		return email.matches(regex);
	}
	
	/**
	 * Check whether the given name (first or last) is accepted or not*/
	public boolean isValidName(String name) {
		String regex = "^[a-zA-Z]{3,}$";
		return name.matches(regex);
	}
	
	/**
	 * Check whether the given age is accepted or not*/
	public boolean isValidAge(int age) {
		return age >= 1 && age <= 40;
	}
	
	/**
	 * Check whether the given phone number is valid or not (Tunisian phone number)*/
	public boolean isVAlidPhoneNumber(String phone) {
		String regex = "^(2|5|9)[0-9]{7}$";
		return phone.matches(regex);
	}
	
	/**
	 * Check whether the given national id number is valid or not (In Tunisia)*/
	public boolean isValidNationalIdNumber(String nationalIdNumber) {
		String regex = "^(0|1)[0-9]{7}$";
		return nationalIdNumber.matches(regex);
	}
	
	
}
