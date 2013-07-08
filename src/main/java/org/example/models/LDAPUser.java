package org.example.models;

import javax.persistence.Id;

public class LDAPUser {
	private String id;
	private String displayName;
	private String email;
	private String[] institutions;
	private String[] roles;
	private String[] phoneNumbers;
	private String[] wwws;
	private String[] addresses;
	private boolean isStudent = true;
	
	public LDAPUser() {}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getInstitutions() {
		return institutions;
	}

	public void setInstitutions(String[] institutions) {
		this.institutions = institutions;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String[] getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String[] phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String[] getWwws() {
		return wwws;
	}

	public void setWwws(String[] wwws) {
		this.wwws = wwws;
	}

	public String[] getAddresses() {
		return addresses;
	}

	public void setAddresses(String[] addresses) {
		this.addresses = addresses;
	}
	
	public boolean isStudent() {
		return isStudent;
	}
	
	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}
	
	
}
