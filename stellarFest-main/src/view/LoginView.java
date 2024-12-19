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
import main.Main;

public class LoginView {
	private Label emailLabel, passLabel, titleLabel, regisLbl;
	private TextField emailTF;
	private PasswordField passTF;
	private Button registerBtn, loginBtn;
	private GridPane gp;
	private VBox layout;
	
	public LoginView() {
        titleLabel = new Label("Login Page");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        emailLabel = new Label("Email : ");
        passLabel = new Label("Password : ");

        emailTF = new TextField();
        passTF = new PasswordField();

        emailTF.setPromptText("Enter Email!");
        passTF.setPromptText("Enter Password!");

        regisLbl = new Label("Dont have an account ? ");
        registerBtn = new Button("Register here!");
        loginBtn = new Button("Login");

        gp = new GridPane();
        gp.setAlignment(Pos.CENTER); 
        gp.setPadding(new Insets(20));
        gp.setHgap(10);
        gp.setVgap(10);

        gp.add(emailLabel, 0, 0);
        gp.add(emailTF, 1, 0);
        gp.add(passLabel, 0, 1);
        gp.add(passTF, 1, 1);
        gp.add(regisLbl, 0, 2);
        gp.add(registerBtn, 1, 2);
        gp.add(loginBtn, 1, 3);

        

//        registerBtn.setOnAction(e -> {
//            RegisterView registerView = new RegisterView();
//            Scene registerScene = registerView.getRegisterScene();
//            Main.redirect(registerScene);
//        });
    }
	
	public Scene getLoginScene() {
		layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, gp);
        return new Scene(layout, 400, 300);
    }
	
	public String getEmailInput() {
		return emailTF.getText();
	}
	
	public String getPassInput() {
		return passTF.getText();
	}
	
	public void setLoginButtonAction(EventHandler<ActionEvent> handler) {
		loginBtn.setOnAction(handler);
	}
	
	public void setRegisBtnAction(EventHandler<ActionEvent> handler) {
		registerBtn.setOnAction(handler);
	}
	
	public void tfClear() {
		emailTF.clear();
		passTF.clear();
	}
	
}
