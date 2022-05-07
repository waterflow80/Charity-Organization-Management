package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Notifier {

	/**
	 * Display an alert*/
	public void InputMismatchAlert(String inputField) {
		Alert a = new Alert(AlertType.WARNING);
		a.setContentText("Bad Input !");
		a.setTitle("Input Mismatch");
		a.setHeaderText(inputField);
		a.show();
	}
}
