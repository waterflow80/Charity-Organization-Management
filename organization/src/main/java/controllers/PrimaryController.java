package controllers;

import java.io.IOException;

import org.charityOrganization.organization.App;

import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("views/login");
    }
}
