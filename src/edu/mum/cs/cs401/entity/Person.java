package edu.mum.cs.cs401.entity;

import java.util.List;

public class Person {

	private String firstName;
	private String lastName;
	private List<String> phone;
	private List<Address> addresses;
	private int id;
	private String password;
	private List<Role> roles;

	public Person(String firstName, String lastName, List<String> phone, List<Address> addresses,
			int id, String password, List<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.id = id;
		this.password = password;
		this.roles = roles;
		this.addresses = addresses;
	}

	public Person() {
	}

	/*  Getters and Setters*/
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

	public List<String> getPhone() {
		return phone;
	}

	public void setPhone(List<String> phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
