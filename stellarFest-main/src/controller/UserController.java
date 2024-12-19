	package controller;
	
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.scene.Scene;
	import javafx.scene.control.Alert.AlertType;
	import javafx.stage.Stage;
	import main.Main;
	import model.User;
	import utils.Connect;
	import utils.showAlert;
import view.ChangeProfileView;
import view.LoginView;
	import view.MainView;
	import view.RegisterView;
	
	public class UserController {
		private static User currentUser;
		private LoginView loginView;
		private RegisterView registerView;
		private MainView mainView;
		private ChangeProfileView changeProfileView;
		private Connect connect;
		private Stage stage;
		public UserController(Stage stage,LoginView loginView, RegisterView registerView) {
			this.stage = stage;
			this.loginView = loginView;
			this.registerView = registerView;
			this.connect = Connect.getInstance();
		}
		
		public void ChangeProfile() {
			changeProfileView = new ChangeProfileView();
			stage.setScene(changeProfileView.getChangeProfileScene());
			
			changeProfileView.setEmailInput(currentUser.getUserEmail());
			changeProfileView.setNameInput(currentUser.getUserName());
			
			changeProfileView.setChangeProfileButtonAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String email = changeProfileView.getEmailInput();
					String name = changeProfileView.getNameInput();
					String oldPass = changeProfileView.getOldPass();
					String newPass = changeProfileView.getNewPass();
					
					if(email == null || name == null || oldPass == null || newPass == null) {
						showAlert.Alerts(AlertType.ERROR, "Change Profile Failed", "Text Field cannot be blank!");
						return;
					}
					
					if(!isEmailUnique(email) && !email.equals(currentUser.getUserEmail())) {
						showAlert.Alerts(AlertType.ERROR, "Change Profile Failed", "Email must be unique");
						return;
					} 
					
					if(!isUsernameUnique(name) && !email.equals(currentUser.getUserName())) {
						showAlert.Alerts(AlertType.ERROR, "Change Profile Failed", "Username must be unique");
						return;
					}
					
					if(!oldPass.equals(currentUser.getUserPassword())) {
						showAlert.Alerts(AlertType.ERROR, "Change Profile Failed", "Old password is incorrect.");
		                return;
					}
					
					if(newPass.length() < 5) {
						showAlert.Alerts(AlertType.ERROR, "Change Profile Failed", "New password must be at least 5 characters long.");
		                return;
					}
					
					String updatedEmail = email.equals(currentUser.getUserEmail()) ? null : email;
		            String updatedName = name.equals(currentUser.getUserName()) ? null : name;
		            String updatedPassword = newPass.equals(currentUser.getUserPassword()) ? null : newPass;
		            boolean isUpdated = updateUser(updatedEmail, updatedName, updatedPassword, currentUser.getUserRole());
		            
		            if (isUpdated) {
		                showAlert.Alerts(AlertType.INFORMATION, "Profile Updated", "Your profile has been updated successfully.");
		                
		                // Update currentUser with new details
		                if (updatedEmail != null) currentUser.setUserEmail(email);
		                if (updatedName != null) currentUser.setUserName(name);
		                if (updatedPassword != null) currentUser.setUserPassword(newPass);
		                
		                MainView mainView = new MainView(currentUser.getUserName(), currentUser.getUserRole());
		                stage.setScene(mainView.getMainScene());
		            } else {
		                showAlert.Alerts(AlertType.ERROR, "Update Failed", "An error occurred while updating your profile.");
		            }
				}
			});
		}
		
		private boolean updateUser(String email, String name, String password, String role) {
		    StringBuilder query = new StringBuilder("UPDATE users SET ");
		    boolean hasUpdate = false;

		    if (email != null) {
		        query.append("userEmail = ?, ");
		        hasUpdate = true;
		    }
		    if (name != null) {
		        query.append("userName = ?, ");
		        hasUpdate = true;
		    }
		    if (password != null) {
		        query.append("userPassword = ?, ");
		        hasUpdate = true;
		    }

		    if (hasUpdate) {
		        query.setLength(query.length() - 2);
		        query.append(" WHERE userId = ?");
		    } else {
		        return false;
		    }

		    try (PreparedStatement ps = connect.prepareStatement(query.toString())) {
		        int index = 1;

		        if (email != null) ps.setString(index++, email);
		        if (name != null) ps.setString(index++, name);
		        if (password != null) ps.setString(index++, password);

		        ps.setString(index, currentUser.getUserId());

		        return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false;
		}

		
		
		public void Logout() {
			System.out.println(currentUser);
			mainView = new MainView(currentUser.getUserName(), currentUser.getUserRole());
			System.out.println("logout clicked");	
			currentUser = null;
			stage.setScene(loginView.getLoginScene());
			showAlert.Alerts(AlertType.INFORMATION, "Logout", "Logout");
			loginView.tfClear();
		}
		
		public void Register() {
			registerView.setRegisterbuttonAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					String email = registerView.getEmailInput();
					String name = registerView.getNameInput();
					String password = registerView.getPasswordInput();
					String role = registerView.getRoleInput();
					
					if(email == null || name == null || password == null || role == null || email.trim().isEmpty() || name.trim().isEmpty() || password.trim().isEmpty() || role.trim().isEmpty()) {
						showAlert.Alerts(AlertType.ERROR, "Register Failed", "Text Field cannot be blank!");
						return;
					}
					
					if(!isEmailUnique(email)) {
						showAlert.Alerts(AlertType.ERROR, "Register Failed", "Email is already registered");
						return;
					}
					
					if(!isUsernameUnique(name)) {
						showAlert.Alerts(AlertType.ERROR, "Register Failed", "Username is already registered");
						return;
					}
					
					if(password.length() < 5) {
						showAlert.Alerts(AlertType.ERROR, "Register Failed", "password must be at least 5 characters long.");
						return;
					}
					
					if(inputUser(email, name, password, role)) {
						showAlert.Alerts(AlertType.INFORMATION, "Success", "User registered successfully");
					} else {
						showAlert.Alerts(AlertType.ERROR, "Database Error", "Failed to register user. Please try again!");
					}
					
				}
			});
			
			registerView.setLoginBtn(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					stage.setScene(loginView.getLoginScene());
				}
			});
		}
		
		
		
		public void Login() {
		    loginView.setLoginButtonAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		            String email = loginView.getEmailInput();
		            String password = loginView.getPassInput();
		            System.out.println(email);
		            System.out.println(password);
		            if(email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty() ) {
						showAlert.Alerts(AlertType.ERROR, "Login Failed", "Text Field cannot be blank!");
						return;
					}
		            if (validateLogin(email, password)) {
		                System.out.println("Login successful!");
		                // Redirect to another scene
		                MainView mainView = new MainView(currentUser.getUserName(), currentUser.getUserRole());
		                stage.setScene(mainView.getMainScene());
		                mainView.setLogoutBtnAction(e-> Logout());
		                mainView.setChangeProfileBtnAction(e -> ChangeProfile());
		                
		            } else {
		                showAlert.Alerts(AlertType.ERROR, "Login Failed!", "Input valid email or password!");
		                return;
		            }
		        }
		    });
		    
		    loginView.setRegisBtnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					stage.setScene(registerView.getRegisterScene());
					
				}
			});
		}
		
		private boolean isEmailUnique(String email) {
			String query = "SELECT COUNT(*) AS count FROM users WHERE userEmail = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			try {
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					return rs.getInt("count") == 0;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
		}
		
		private boolean isUsernameUnique(String name) {
			String query = "SELECT COUNT(*) AS count FROM users WHERE userName = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			try {
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					return rs.getInt("count") == 0;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
		}
		
		private boolean inputUser(String email, String name, String password, String role) {
			String query = "INSERT INTO users (userEmail, userName, userPassword, userRole) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = connect.prepareStatement(query);
			try {
				ps.setString(1, email);
				ps.setString(2, name);
				ps.setString(3, password);
				ps.setString(4, role);
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
		}
	
		private boolean validateLogin(String email, String password) {
		    String query = "SELECT * FROM users WHERE userEmail = ? AND userPassword = ?";
		    try (PreparedStatement ps = connect.prepareStatement(query)) {
		        ps.setString(1, email);
		        ps.setString(2, password);
	
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            String userId = rs.getString("userId");
		            String userName = rs.getString("userName");
		            String userRole = rs.getString("userRole");
	
		            User user = new User(userId, email, userName, password, userRole);
		            UserController.setCurrentUser(user);
		            return true; // Login successful
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false; // Login failed
		}
		
		
	
		
		public static void logout() {
			currentUser = null;
		}
	
		public static void setCurrentUser(User user) {
			currentUser = user;
		}
		
		public static User getCurrentUser() {
			return currentUser;
		}
	}
