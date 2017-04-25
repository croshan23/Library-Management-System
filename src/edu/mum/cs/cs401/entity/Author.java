package edu.mum.cs.cs401.entity;

public class Author {

	private String firstName;
	
	private String lastName;
	
	private String bio;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Author(String firstName, String lastName, String bio) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bio = bio;
	}
	
	public Author() {
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
