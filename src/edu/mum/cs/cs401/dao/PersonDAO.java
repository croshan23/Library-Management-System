package edu.mum.cs.cs401.dao;

import java.util.List;

import edu.mum.cs.cs401.entity.Person;
import edu.mum.cs.cs401.entity.Role;

public interface PersonDAO {
	
	public boolean addPerson(Person person);
	
	public boolean addPersons(List<Person> persons);
	
	public Person login(String id, String password);
	
	public Person search(String id, Role role);
	
	public List<Person> getAll(Role role);
	
	public void loadPersons();
	
}
