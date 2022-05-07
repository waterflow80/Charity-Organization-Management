package controllers;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entities.FamilyMember;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AddFamilyController implements Initializable{

	private int column = 0;
	private int row = 0;
	private int numberOfParents = 0;
	private String childCount = "1"; // The child's count (to see the number of children overall)

	private List<ChildItemController> childrenControllers = new ArrayList<>(); // A list of all child items controllers
	
	@FXML
    private VBox ChildrenVBox;
	
	@FXML
    private Button addParentBtn;
	
	@FXML
    private Button addChildBtn;
	
	@FXML
    private GridPane navBar;
	
	 @FXML
	 private GridPane parentGrid;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			//Adding the nav-bar on the top of the page
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/org/charityOrganization/organization/views/admin/navigation/navBar.fxml"));
			AnchorPane anchorPane = fxmlLoader.load();
			navBar.add(anchorPane, 0, 0);
			
			// Adding the first parent form (item) to the page
			FXMLLoader parentLoader = new FXMLLoader();
			parentLoader.setLocation(getClass().getResource("/org/charityOrganization/organization/views/admin/families/parentItem.fxml"));
			AnchorPane parentAnchorPane = parentLoader.load();
			ParentItemController parentItemController = parentLoader.getController();
			parentGrid.add(parentAnchorPane, column++, row);
			parentItemController.setRow(column); // Setting the row number where the item has been added
			parentItemController.setColumn(row); // Setting the column number where the item has been added
			parentItemController.setAddFamilyController(this); // Setting the reference controller for the parent item 
			numberOfParents ++; // incrementing the number of parents by one
		} catch (Exception e) {
			System.out.println("Failed to load"); 
			e.printStackTrace();
		}
		
	}
	
	public void removeParentNodeByRowColumnIndex(final int row,final int column) {

		ObservableList<Node> childrens = parentGrid.getChildren();
		for(Node node : childrens) {
		    if(node instanceof AnchorPane && parentGrid.getRowIndex(node) == row && parentGrid.getColumnIndex(node) == column) {
		        AnchorPane anchorPane = new AnchorPane(node);
		        parentGrid.getChildren().remove(anchorPane);
		        break;
		    }
		  } 
		// Decrementing the number of parents by one, and setting the availability of the addParent button
		numberOfParents --;
		if (numberOfParents < 2)
			addParentBtn.setDisable(false);
		if (column == 0) {
			this.row --;
			this.column = 1;
		}else {
			this.column --;
		}
	}
	
	@FXML
    void addParentHoverd(MouseEvent event) {
		addParentBtn.setStyle("-fx-background-color: #1d6b0d;");
    }
	
	@FXML
    void addParentUnHovered(MouseEvent event) {
		addParentBtn.setStyle("-fx-background-color: #279613;");
    }
	
	 @FXML
	 void addChildHoverd(MouseEvent event) {
		 addChildBtn.setStyle("-fx-background-color: #1d6b0d;");
	  }
	
	 @FXML
	 void addChildUnHovered(MouseEvent event) {
		 addChildBtn.setStyle("-fx-background-color: #279613;");
	 }
	
	@FXML
	/**
	 * Adding a new parent item (form) to the family*/
    void addParent(ActionEvent event) {
		// Setting the right dimensions
		if (column == 2) {
			row ++;
			column = 0;
		}
		
		try {
			// Loading the parent item (fxml file and it's associated controller)
			FXMLLoader parentLoader = new FXMLLoader();
			parentLoader.setLocation(getClass().getResource("/org/charityOrganization/organization/views/admin/families/parentItem2.fxml"));
			AnchorPane parentAnchorPane = parentLoader.load();
			ParentItemController parentItemController = parentLoader.getController();
			
			
			parentGrid.add(parentAnchorPane, column, row);
			//Set grid width
			parentGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
			parentGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
			parentGrid.setMaxWidth(Region.USE_PREF_SIZE);
			
			//Set grid height
			parentGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
			parentGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
			parentGrid.setMaxHeight(Region.USE_PREF_SIZE);
			
			GridPane.setMargin(parentAnchorPane, new Insets(10));
			
			parentItemController.setRow(row); // Setting the row number where the item has been added
			parentItemController.setColumn(column); // Setting the column number where the item has been added
			parentItemController.setAddFamilyController(this); // Setting the reference controller for the parent item 
			column ++;
			
			//Incrementing the numberOfParents and checking if we've exceeded the maximum number of parents, if so, disable the add parent button
			numberOfParents ++;
			if (numberOfParents == 2)
				addParentBtn.setDisable(true);
			
			
		} catch (Exception e) {
			System.out.println("Failed to load parent item"); 
			e.printStackTrace();
		}
		
    }
	
	@FXML
	/**
	 * Adding a new child item (form) to the family*/
    void addChild(ActionEvent event) {
		try {
			// Loading the parent item (fxml file and it's associated controller)
			FXMLLoader childLoader = new FXMLLoader();
			childLoader.setLocation(getClass().getResource("/org/charityOrganization/organization/views/admin/families/childItem.fxml"));
			AnchorPane ChildAnchorPane = childLoader.load();
			ChildItemController childItemController = childLoader.getController();
			childItemController.setCounter(childCount); // Setting the child's count to be displayed in the child item
			childCount = Integer.toString(Integer.parseInt(childCount) + 1); // Incrementing the child's count
			childItemController.setAddFamilyController(this);
			this.childrenControllers.add(childItemController); // Adding the newly added child item's controllers to the list
				
			ChildrenVBox.getChildren().add(ChildAnchorPane);
	
			
		} catch (Exception e) {
			System.out.println("Failed to load child item"); 
			e.printStackTrace();
		}
    }
	
	void removeChildItem(AnchorPane childAnchorPane, ChildItemController childController) {
		ObservableList<Node> children = ChildrenVBox.getChildren();
		childrenControllers.remove(childController);
		System.out.println(childController);
		this.childrenControllers.remove(childController); // Removing the associated controller from the list
		children.remove(childAnchorPane);
		// Decrementing the overall number of children
		childCount = Integer.toString(Integer.parseInt(childCount) - 1);
	}
	

	@FXML
	/**
	 * Submitting the from and saving the information to the dataBase*/
    void submit(ActionEvent event) {
		List<FamilyMember> allChildren = new ArrayList<>();
		for (int i=0; i<childrenControllers.size(); i++) {
			FamilyMember fm = childrenControllers.get(i).getData();
			allChildren.add(fm);
		}
		
    }
}
