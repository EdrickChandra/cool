package model;

import java.util.List;

public class Guest  extends User{
	List<Invitation> acceptedInvitations;

	public Guest(String userId, String userEmail, String userName, String userPassword, String userRole,
			List<Invitation> acceptedInvitations) {
		super(userId, userEmail, userName, userPassword, userRole);
		this.acceptedInvitations = acceptedInvitations;
	}

	public List<Invitation> getAcceptedInvitations() {
		return acceptedInvitations;
	}

	public void setAcceptedInvitations(List<Invitation> acceptedInvitations) {
		this.acceptedInvitations = acceptedInvitations;
	}
	
	
}
