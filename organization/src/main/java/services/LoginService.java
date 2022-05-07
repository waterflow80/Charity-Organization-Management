package services;

import entities.Account;
import exceptions.AuthentificationFailureException;


public interface LoginService {
	public Account loginUser(String email, String password) throws AuthentificationFailureException; // return the accountId of the logged in user
}
