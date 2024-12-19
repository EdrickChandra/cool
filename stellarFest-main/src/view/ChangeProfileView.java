package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChangeProfileView {
	private Label titleLabel, emailLabel, nameLabel, newPassLabel, oldPassLabel;
	private TextField emailTF, nameTF;
	private PasswordField newPassTF, oldPassTF;
	private Button changeProfileBtn;
	private GridPane gp;
	private VBox layout;
	
	public Scene getChangeProfileScene() {
		titleLabel = new Label("Change Profile Page");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		emailLabel = new Label("Email : ");
		nameLabel = new Label("Name : ");
		newPassLabel = new Label("New Password : ");
		oldPassLabel = new Label("Old Password : ");
		
		emailTF = new TextField();
		nameTF = new  TextField();
		newPassTF = new PasswordField();
		oldPassTF = new PasswordField();
		
		changeProfileBtn = new Button("Change Profile!");
		
		gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(20));
		gp.setHgap(10);
		gp.setVgap(10);
		
		gp.add(nameLabel, 0, 0);
		gp.add(nameTF, 1, 0);
		
		gp.add(emailLabel, 0, 1);
		gp.add(emailTF, 1, 1);
		
		gp.add(oldPassLabel, 0, 2);
		gp.add(oldPassTF, 1, 2);
		
		gp.add(newPassLabel, 0, 3);
		gp.add(newPassTF, 1, 3);
		
		gp.add(changeProfileBtn, 1, 4);
		
		layout = new VBox(20);
		layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel,gp);
		
		return new Scene(layout,400,300);
	}
	
	public String getEmailInput() {
		return emailTF.getText();
	}
	
	public String getNameInput() {
		return nameTF.getText();
	}
	
	public String getOldPass() {
		return oldPassTF.getText();
	}
	
	public String getNewPass() {
		return newPassTF.getText();
	}
	
	public void setNameInput(String name) {
		nameTF.setText(name);
	}
	
	public void setEmailInput(String email) {
		emailTF.setText(email);
	}
	
	public void setChangeProfileButtonAction(EventHandler<ActionEvent> handler) {
		changeProfileBtn.setOnAction(handler);
	}
}
