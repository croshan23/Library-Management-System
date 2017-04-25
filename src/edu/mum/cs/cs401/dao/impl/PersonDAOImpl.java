package edu.mum.cs.cs401.dao.impl;

import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.dao.DataAccess;
import edu.mum.cs.cs401.dao.PersonDAO;
import edu.mum.cs.cs401.entity.Person;
import edu.mum.cs.cs401.entity.Role;

public class PersonDAOImpl implements PersonDAO {
	
	private PersonDAOImpl() {
		loadPersons();
	}
	
	private static PersonDAOImpl personDAO = new PersonDAOImpl();
	
	public static PersonDAOImpl getInstance() {
		return personDAO;
	}
	
	private String cdr = System.getProperty("user.dir");
	private String personJson = cdr + "/src/edu/mum/cs/cs401/dao/person.json";
	 
	private static List<Person> listOfPerson;
	
	@Override
	public Person login(String id, String password) {
		Person search = search(id, null);
		if (search != null && password.equals(search.getPassword())) {
			return search;
		}
		return null;
	}

	@Override
	public boolean addPersons(List<Person> persons) {
		listOfPerson.addAll(persons);
		DataAccess.save(listOfPerson, personJson);
		return true;
	}
	
	@Override
	public boolean addPerson(Person person) {
		listOfPerson.add(person);
		DataAccess.save(listOfPerson, personJson);
		return true;
	}
	
	@Override
	public void loadPersons(){
		listOfPerson = DataAccess.getList(personJson);
	}

	@Override
	public Person search(String id, Role role) {
		Person temPerson = null;
		int parseInt;
		try {
			parseInt = Integer.parseInt(id); 
		} catch(Exception e) {
			System.out.println("can not parse " + id);
			return null;
		}
		for(Person p: listOfPerson){
			if(p.getId() == parseInt){
				if (role == null || p.getRoles().contains(role)) {
					temPerson = p;
				}
			}
			
		}
		return temPerson;
	}

	@Override
	public List<Person> getAll(Role role) {
		List<Person> list = new ArrayList<Person>();
		for(Person p: listOfPerson){
			if(p.getRoles().contains(role)){
				list.add(p);
			}
		}
		return list;
	}
	

}
