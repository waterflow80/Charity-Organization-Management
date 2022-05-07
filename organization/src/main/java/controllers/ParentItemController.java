package controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import model.Parent;

public class ParentItemController implements Initializable{
	
	private int row; // The row where the instance of this item is located in the gridPane of the main window (addFamily)
	private int column; // The row where the instance of this item is located in the grid (addFamily)
	private AddFamilyController addFamilyController; // The family controller associated with the addFamil.fxml where the parent item is added
	
	private final List<String> parentTypeOptions = Arrays.asList("Mother", "Father"); // The options for the parent type
	private final List<String> professionOptions = Arrays.asList("Unemployed", "Laborer", "Employee"); 
	
	@FXML
    private TextField NationalId;

    @FXML
    private TextField age;

    @FXML
    private TextField email;

    @FXML
    private TextField fName;

    @FXML
    private TextField income;

    @FXML
    private TextField lName;

    @FXML
    private ComboBox parentCombo;

    @FXML
    private TextField phone;

    @FXML
    private ComboBox professionCombo;

    @FXML
    private Button removeBtn;

    @FXML
    void removeParentItem(ActionEvent event) {
    	addFamilyController.removeParentNodeByRowColumnIndex(row, column);
    }

    @FXML
    void selectParent(ActionEvent event) {
    	String parentType = parentCombo.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void selectProfession(ActionEvent event) {
    	String profession = professionCombo.getSelectionModel().getSelectedItem().toString();
    	System.out.println(profession);
    }


		@Override
		public void initialize(URL location, ResourceBundle resources) {
			ObservableList<String> parentTypeList = FXCollections.observableArrayList(parentTypeOptions);
			parentCombo.setItems(parentTypeList);
			ObservableList<String> professionsList = FXCollections.observableArrayList(professionOptions);
			professionCombo.setItems(professionsList);
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}

		public AddFamilyController getAddFamilyController() {
			return addFamilyController;
		}

		public void setAddFamilyController(AddFamilyController addFamilyController) {
			this.addFamilyController = addFamilyController;
		}

		
		
		
}
