package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class showAlert {
	public static void Alerts(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
