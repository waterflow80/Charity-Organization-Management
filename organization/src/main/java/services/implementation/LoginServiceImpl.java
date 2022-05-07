package services.implementation;

import dao.AccountDAO;
import entities.Account;
import exceptions.AuthentificationFailureException;
import services.LoginService;
import utils.InputChecker;

public class LoginServiceImpl implements LoginService {

	@Override
	/**
	 * Login the user and return the associated Account instance */
	public Account loginUser(String email, String password) throws AuthentificationFailureException {
		Account account = AccountDAO.getAccountByEmail(email);
		InputChecker check = new InputChecker();
		if (!check.isValidEmail(email))
				throw new AuthentificationFailureException("Invalid email !");
		if (account == null)
			throw new AuthentificationFailureException("No Account found with email: " + email + " !");
		if (account.getPassword().compareTo(password) != 0)
			throw new AuthentificationFailureException("Wrong password !");
		return account; // Logged in successfully !
		
	}


}
