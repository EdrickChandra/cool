package model;

import java.util.List;

public class Vendor {
	List<Invitation> acceptedInvitations;

	public Vendor(List<Invitation> acceptedInvitations) {
		super();
		this.acceptedInvitations = acceptedInvitations;
	}

	public List<Invitation> getAcceptedInvitations() {
		return acceptedInvitations;
	}

	public void setAcceptedInvitations(List<Invitation> acceptedInvitations) {
		this.acceptedInvitations = acceptedInvitations;
	}
	
	

}
