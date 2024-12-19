package main;
import controller.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import view.LoginView;
import view.MainView;
import view.RegisterView;

public class Main extends Application {
	private static Stage stage;

	public static void redirect(Scene newScene) {
		stage.setScene(newScene);
		stage.centerOnScreen();
		stage.setResizable(false);
		stage.show();
	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
			stage = primaryStage;	
			LoginView loginView = new LoginView();
			RegisterView registerView = new RegisterView();
			UserController userController = new UserController(primaryStage, loginView, registerView);
			
			userController.Login();
			userController.Register();
			
			
			primaryStage.setTitle("StellarFest");
			primaryStage.setScene(loginView.getLoginScene());
			primaryStage.show();
	}
	
	

}
