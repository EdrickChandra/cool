package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;

public class RegisterView {
	private Label titleLabel, emailLabel, nameLabel, passwordLabel, roleLabel, loginLbl;
	private TextField emailTF, nameTF;
	private PasswordField passTF;
	private ComboBox<String> roleCB;
	private Button registerBtn, loginBtn;
	private GridPane gp;
	private VBox layout;
	
	public RegisterView() {
		titleLabel = new Label("Registration Page");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		emailLabel = new Label("Email : ");
		nameLabel = new Label("Name : ");
		passwordLabel = new Label("Password : ");
		roleLabel = new Label("Role : ");
		
		emailTF = new TextField();
		nameTF = new TextField();
		passTF = new PasswordField();
		roleCB = new ComboBox<String>();
		roleCB.getItems().addAll("Admin", "Vendor", "Guest", "Event Organizer");
		
		emailTF.setPromptText("Enter Email!");
		nameTF.setPromptText("Enter Name!");
		passTF.setPromptText("Enter Password");
		roleCB.setPromptText("Select a Role!");
		
		loginLbl = new Label("Have an account ?");
		loginBtn = new Button("Login Here!");
		registerBtn = new Button("Register!");
		
		gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(20));
		gp.setHgap(10);
		gp.setVgap(10);
		
		gp.add(emailLabel, 0, 0);
		gp.add(emailTF, 1, 0);
		
		gp.add(nameLabel, 0, 1);
        gp.add(nameTF, 1, 1);
        
        gp.add(passwordLabel, 0, 2);
        gp.add(passTF, 1, 2);

        gp.add(roleLabel, 0, 3);
        gp.add(roleCB, 1, 3);
        
        gp.add(loginLbl,0,4);
        gp.add(loginBtn, 1, 4);
        gp.add(registerBtn, 1, 5);
        
        
        
//        loginBtn.setOnAction(e -> {
//            LoginView loginView = new LoginView();
//            Scene loginScene = loginView.getLoginScene();
//            Main.redirect(loginScene);
//        });
	}
	
	public Scene getRegisterScene() {
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
	
	public String getPasswordInput() {
		return passTF.getText();
	}
	
	public String getRoleInput() {
		return roleCB.getValue();
	}
	
	public void setRegisterbuttonAction(EventHandler<ActionEvent> handler) {
		registerBtn.setOnAction(handler);
	}
	
	public void setLoginBtn(EventHandler<ActionEvent> handler) {
		loginBtn.setOnAction(handler);
	}
}
