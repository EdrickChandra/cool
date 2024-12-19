package model;

public class Invitation {
	private String invitationId;
	private String eventId;
	private String userId;
	private String invitationStatus;
	private String invitationRole;
	public Invitation(String invitationId, String eventId, String userId, String invitationStatus,
			String invitationRole) {
		super();
		this.invitationId = invitationId;
		this.eventId = eventId;
		this.userId = userId;
		this.invitationStatus = invitationStatus;
		this.invitationRole = invitationRole;
	}
	public String getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInvitationStatus() {
		return invitationStatus;
	}
	public void setInvitationStatus(String invitationStatus) {
		this.invitationStatus = invitationStatus;
	}
	public String getInvitationRole() {
		return invitationRole;
	}
	public void setInvitationRole(String invitationRole) {
		this.invitationRole = invitationRole;
	}
	
	
}
