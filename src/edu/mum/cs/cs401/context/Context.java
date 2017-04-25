package edu.mum.cs.cs401.context;

import edu.mum.cs.cs401.entity.Person;
import edu.mum.cs.cs401.view.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Context {
	
	private Context() {
	}
	
	private static Context personContext = new Context();
	
	public static Context getInstance() {
		return personContext;
	}
	
	private Person user;
	
	public void setUser(Person person) {
		user = person;
	}
	
	public Person getUser() {
		return user;
	}
	
	public void changeScreen(Stage root, View view) {
		root.setScene(view.getScene());
	}
	
	public void changeScreen(ActionEvent actionEvent, View view) {
		Stage root = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		changeScreen(root, view);
	}
}
