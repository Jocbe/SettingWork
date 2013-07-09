package org.example.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

public class LDAPUser {
	private String id = null;
	private String displayName = null;
	private String email = null;
	private List<String> institutions = null;
	private List<String> roles = null;
	private List<String> phoneNumbers = null;
	private List<String> wwws = null;
	private List<String> addresses = null;
	private boolean isStudent = true;
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	private String image = null;
	
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

	public List<String> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<String> institutions) {
		this.institutions = institutions;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<String> getWwws() {
		return wwws;
	}

	public void setWwws(List<String> wwws) {
		this.wwws = wwws;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}
	
	public boolean isStudent() {
		return isStudent;
	}
	
	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}
	
	
}
