package controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import entities.FamilyMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.InputChecker;
import utils.Notifier;

public class ChildItemController implements Initializable{
	private final List<String> professionOptions = Arrays.asList("Student", "Droput", "Laborer"); 

	private AddFamilyController addFamilyController; // The family controller associated with the addFamil.fxml where the parent item is added
	
	
	 @FXML
	 private AnchorPane childAnchorPane;
	
    @FXML
    private TextField NationalId;

    @FXML
    private TextField age;

    @FXML
    private Label childCounter;

    @FXML
    private TextField email;

    @FXML
    private TextField fName;

    @FXML
    private TextField income;

    @FXML
    private TextField lName;

    @FXML
    private TextField phone;

    @FXML
    private ComboBox professionCombo;

    @FXML
    private Button removeBtn;

    @FXML
    void removeChildItem(ActionEvent event) {
    	addFamilyController.removeChildItem(childAnchorPane, this);
    }

    @FXML
    void selectProfession(ActionEvent event) {
    	String profession = professionCombo.getSelectionModel().getSelectedItem().toString();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> professionsList = FXCollections.observableArrayList(professionOptions);
		professionCombo.setItems(professionsList);
		
		
	}


	public void setCounter(String counter) {
		childCounter.setText(counter);
	}

	public AddFamilyController getAddFamilyController() {
		return addFamilyController;
	}

	public void setAddFamilyController(AddFamilyController addFamilyController) {
		this.addFamilyController = addFamilyController;
	}
	
	
	/**
	 * Return the FamilyMember (child) object's data after making the checks*/
	FamilyMember getData() {
		InputChecker checker = new InputChecker();
		Notifier alerter = new Notifier();
		if (!checker.isValidName(fName.getText())) {
			alerter.InputMismatchAlert("First Name");
			return null;
		}
		if (!checker.isValidName(lName.getText())) {
			alerter.InputMismatchAlert("Last Name");
			return null;
		}
		try {
			if (!checker.isValidAge(Integer.parseInt(age.getText()))) {
				alerter.InputMismatchAlert("Age");
				return null;
			}
		} catch (Exception e) {
			alerter.InputMismatchAlert("Age");
			return null;
		}
		
		if (! checker.isVAlidPhoneNumber(phone.getText())) {
			alerter.InputMismatchAlert("Phone Number");
			return null;
		}
		
		if (!checker.isValidNationalIdNumber(NationalId.getText())) {
			alerter.InputMismatchAlert("National Id Number");
			return null;
		}
		if (!checker.isValidEmail(email.getText())) {
			alerter.InputMismatchAlert("Email");
			return null;
		}
		if (professionCombo.getSelectionModel().isEmpty()) { // No profession selected
			alerter.InputMismatchAlert("Pofession");
			return null;
		}
		
		
		// Setting up the data
		String firstName = fName.getText().toString();
		String lastName = lName.getText().toString();
		int age = Integer.parseInt(this.age.getText());
		String phoneNumber = phone.getText();
		String nationalIdNumber = NationalId.getText();
		String email = this.email.getText();
		String profession =(String) professionCombo.getSelectionModel().getSelectedItem();
		Float income;
		try {
			income = Float.parseFloat(this.income.getText());
		} catch (Exception e) {
			alerter.InputMismatchAlert("Income");
			return null;
		}
		
		return new FamilyMember(firstName, lastName, age,profession, nationalIdNumber, phoneNumber, income, false);
	}
	
	
	
}
