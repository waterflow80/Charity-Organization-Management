package controllers;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


import entities.Member;
import exceptions.EntityNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import services.MemberService;
import services.implementation.MemberServiceImpl;

public class MembersController implements Initializable{
	MemberService memberService = new MemberServiceImpl();
	
	@FXML
    private TableColumn<Member, String> firstNameCol;

    @FXML
    private TableColumn<Member, Integer> idCol;

    @FXML
    private TableColumn<Member, String> lastNameCol;

    @FXML
    private TableView<Member> membersTable;

    @FXML
    private TableColumn<Member, String> nationalIdCol;

    @FXML
    private TableColumn<Member, String> phoneNumberCol;

    @FXML
    private TableColumn<Member, String> roleCol;

	@Override
	/**
	 * This method will be called automatically upon fxml load*/
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Member, Integer>("memberId"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("firstName"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("lastName"));
		nationalIdCol.setCellValueFactory(new PropertyValueFactory<Member, String>("nationalIdNumber"));
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNumber"));
		roleCol.setCellValueFactory(new PropertyValueFactory<Member, String>("role"));
		displayAllMembers();
	}
	
	public void displayAllMembers() {
		try {
			List<Member> members = memberService.getAllMembers();
			ObservableList<Member> membersList = FXCollections.observableArrayList();
			for (Member m: members)
				membersList.add(m);
		
			membersTable.setItems(membersList);
		} catch (EntityNotFoundException e) {
			// Failed to get all members
			// Creating an alert
			Alert a = new Alert(AlertType.WARNING);
			a.setContentText("Failed to get all members from the db.");
			a.setTitle("Database Fetch Failure");
			a.setHeaderText(e.getMessage());
			a.show();
		}
	}
}
