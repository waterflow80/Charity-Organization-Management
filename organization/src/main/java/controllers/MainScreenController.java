package controllers;

import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.charityOrganization.organization.App;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    private AnchorPane bottomPane;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private Pane topPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLeftPane();
        initBottomPane();
        initTopPane();
    }

    public void initLeftPane() {
        try {
            VBox toolbar = FXMLLoader.load(App.class.getResource("views/admin/navigation/toolbar.fxml"));
            leftPane.getChildren().setAll(toolbar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void initBottomPane() {
        try {
            AnchorPane addFamilyLayout = FXMLLoader.load(App.class.getResource("views/admin/families/addFamily.fxml"));
            bottomPane.getChildren().setAll(addFamilyLayout);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initTopPane() {
        TableView tableView = new TableView();
        tableView.setPrefWidth(Integer.MAX_VALUE);
        tableView.setMaxWidth(Integer.MAX_VALUE);

        TableColumn<Map, String> firstNameColumn = new TableColumn<>("firstName");
        firstNameColumn.setCellValueFactory(new MapValueFactory<>("firstName"));

        TableColumn<Map, String> lastNameColumn = new TableColumn<>("lastName");
        lastNameColumn.setCellValueFactory(new MapValueFactory<>("lastName"));

        tableView.getColumns().add(firstNameColumn);
        tableView.getColumns().add(lastNameColumn);


        ObservableList<Map<String, Object>> items =
                FXCollections.<Map<String, Object>>observableArrayList();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("firstName", "Randall");
        item1.put("lastName" , "Kovic");

        items.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("firstName", "Irmelin");
        item2.put("lastName" , "Satoshi");

        items.add(item2);

        tableView.getItems().addAll(items);

        topPane.getChildren().setAll(tableView);
    }
}
