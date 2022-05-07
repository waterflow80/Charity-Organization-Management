package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class NavBarController implements Initializable{
	
	@FXML
    private Button btnDonations;

    @FXML
    private Button btnExpenses;

    @FXML
    private Button btnFamilies;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMembers;

    @FXML
    private Button btnMissions;

    
    @FXML
    void donationsHovered(MouseEvent event) {
    	btnDonations.setStyle("-fx-border-color: #C41E3A;-fx-background-color: #cecdcd;");
    	
    }

    @FXML
    void donationsUnHovered(MouseEvent event) {
    	btnDonations.setStyle("-fx-border-color: none;-fx-background-color: #cecdcd;");
    }

    @FXML
    void expensesHoverd(MouseEvent event) {
    	btnExpenses.setStyle("-fx-border-color: #C41E3A;-fx-background-color: #cecdcd;");
    }

    @FXML
    void expensesUnHoverd(MouseEvent event) {
    	btnExpenses.setStyle("-fx-border-color: none;-fx-background-color: #cecdcd;");
    }

    @FXML
    void familiesHovered(MouseEvent event) {
    	btnFamilies.setStyle("-fx-border-color: #C41E3A;-fx-background-color: #cecdcd;");
    }

    @FXML
    void familiesUnHovered(MouseEvent event) {
    	btnFamilies.setStyle("-fx-border-color: none;-fx-background-color: #cecdcd;");
    }

    @FXML
    void homeHovered(MouseEvent event) {
    	btnHome.setStyle("-fx-border-color: #C41E3A;-fx-background-color: #cecdcd;");
    }

    @FXML
    void homeUnHovered(MouseEvent event) {
    	btnHome.setStyle("-fx-border-color: none;-fx-background-color: #cecdcd;");
    }

    @FXML
    void logoutHovered(MouseEvent event) {
    	btnLogout.setStyle("-fx-border-color: #C41E3A;-fx-background-color: #cecdcd;");
    }

    @FXML
    void logoutUnHovered(MouseEvent event) {
    	btnLogout.setStyle("-fx-border-color: none;-fx-background-color: #cecdcd;");
    }

    @FXML
    void membersHovered(MouseEvent event) {
    	btnMembers.setStyle("-fx-border-color: #C41E3A;-fx-background-color: #cecdcd;");
    }

    @FXML
    void membersUnHovered(MouseEvent event) {
    	btnMembers.setStyle("-fx-border-color: none;-fx-background-color: #cecdcd;");
    }

    @FXML
    void missionsHovered(MouseEvent event) {
    	btnMissions.setStyle("-fx-border-color: #C41E3A;-fx-background-color: #cecdcd;");
    }

    @FXML
    void missionsUnHovered(MouseEvent event) {
    	btnMissions.setStyle("-fx-border-color: none;-fx-background-color: #cecdcd;");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO specifying the dimensions from here (width, min-width, ...)
		
	}
}
