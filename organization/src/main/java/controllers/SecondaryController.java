package controllers;

import java.io.IOException;

import org.charityOrganization.organization.App;

import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("views/primary");
    }
}