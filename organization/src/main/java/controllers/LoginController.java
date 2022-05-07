package controllers;

import org.charityOrganization.organization.App;

import entities.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.LoginService;
import services.implementation.LoginServiceImpl;
import utils.InputChecker;

public class LoginController {
	LoginService loginService = new LoginServiceImpl();
	
	@FXML
    private TextField userLogin;

    @FXML
    private TextField userPassword;

    @FXML
    private Button loginBtn;

    @FXML
    private void loginUser(ActionEvent event) {
    	try {
			Account account = loginService.loginUser(userLogin.getText(), userPassword.getText());
			
			App.setRoot("views/secondary");
		} catch (Exception e) {
			// Failed to login user
			// Creating an alert
			Alert a = new Alert(AlertType.WARNING);
			a.setContentText("Failed to login user.");
			a.setTitle("Authentification Failure");
			a.setHeaderText(e.getMessage());
			a.show();
		}
    }
}
