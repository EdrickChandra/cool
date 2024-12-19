package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainView{
	private Label titleLabel, nameLabel, roleLabel;
	private Button changeProfileBtn, logoutBtn, viewEventBtn, viewUserBtn, viewInvitation, addVendorBtn, addGuestBtn, createEventBtn, viewInvitationBtn, manageVendorBtn;
	private VBox userLayout, layout;
	private HBox header;
	private GridPane gpUser, gp;
	public MainView(String username, String role) {
		titleLabel = new Label("Main Page");
	    titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

	    // User info and buttons
	    nameLabel = new Label("Username: " + username);
	    roleLabel = new Label("Role: " + role);
	    changeProfileBtn = new Button("Change Profile");
	    logoutBtn = new Button("Logout");

	    gpUser = new GridPane();
	    gpUser.setPadding(new Insets(20));
	    gpUser.setHgap(10);
	    gpUser.setVgap(3);
	    gpUser.add(nameLabel, 0, 0);
	    gpUser.add(roleLabel, 0, 1);
	    gpUser.add(changeProfileBtn, 0, 2);
	    gpUser.add(logoutBtn, 1, 2);
	    
	    
	    
	    gp = new GridPane();
	    gp.setAlignment(Pos.CENTER);
	    gp.setPadding(new Insets(10));
        gp.setHgap(10);
        gp.setVgap(10);
        
	    
	   setupRoleBasedNav(role);
		
	
	}
	
	public Scene getMainScene() {
		layout = new VBox(20);
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(5));
		layout.getChildren().addAll(titleLabel,gpUser,gp);
		return new Scene(layout, 550, 300);
	}
	
	private void setupRoleBasedNav(String role) {
		switch (role) {
        case "Admin":
            setupAdminNavigation();
            break;
        case "Event Organizer":
            setupEventOrganizerNavigation();
            break;
        case "Vendor":
            setupVendorNavigation();
            break;
        case "Guest":
            setupGuestNavigation();
            break;
        default:
            System.out.println("Unknown role: " + role);
		}
	}

	private void setupGuestNavigation() {
		// TODO Auto-generated method stub
		viewInvitationBtn = new Button("Invitation");
		viewInvitationBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		viewEventBtn = new Button("Events");
		viewEventBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		
		gp.add(viewInvitationBtn, 0, 0);
		gp.add(viewEventBtn, 1,0);
	}

	private void setupVendorNavigation() {
		// TODO Auto-generated method stub
		viewInvitationBtn = new Button("Invitation");
		viewInvitationBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		viewEventBtn = new Button("Events");
		viewEventBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		manageVendorBtn = new Button("Manage Vendor");
		manageVendorBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		
		gp.add(viewInvitationBtn, 0, 0);
		gp.add(viewEventBtn, 1,0);
		gp.add(manageVendorBtn,2,0);
	}

	private void setupEventOrganizerNavigation() {
		// TODO Auto-generated method stub
		viewEventBtn = new Button("Events");
		viewEventBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		addVendorBtn = new Button("Add Vendor");
		addVendorBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		addGuestBtn = new Button("Add Guests");
		addGuestBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		createEventBtn = new Button("Create Event");
		createEventBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		
		gp.add(viewEventBtn, 0, 0);
		gp.add(addVendorBtn, 1,0);
		gp.add(addGuestBtn, 2,0	);
		gp.add(createEventBtn, 3,0);
	}

	private void setupAdminNavigation() {
		// TODO Auto-generated method stub
		viewEventBtn = new Button("Events");
	    viewEventBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
	    viewUserBtn = new Button("Users");
	    viewUserBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
	    
	    gp.add(viewEventBtn, 0, 0);
	    gp.add(viewUserBtn, 1, 0);
	}
	
	public void setLogoutBtnAction(EventHandler<ActionEvent> handler) {
		logoutBtn.setOnAction(handler);
	}
	
	public void setChangeProfileBtnAction(EventHandler<ActionEvent> handler) {
		changeProfileBtn.setOnAction(handler);
	}
	
}

