package edu.mum.cs.cs401.controller;

import edu.mum.cs.cs401.context.Context;
import edu.mum.cs.cs401.dao.PersonDAO;
import edu.mum.cs.cs401.dao.impl.PersonDAOImpl;
import edu.mum.cs.cs401.entity.Person;
import edu.mum.cs.cs401.view.DashBoardView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends Controller {
	
	private PersonDAO personDA = PersonDAOImpl.getInstance();
	
	@FXML
	private Label messageLabel;

	@FXML
	private TextField idText;
	
	@FXML
	private PasswordField passwordText;
	
	@FXML
	private void login(ActionEvent actionEvent) {
		Person person = personDA.login(idText.getText(), passwordText.getText());
		if (person == null) {
			messageLabel.setText("Invalid username/password");
		} else {
			Context.getInstance().setUser(person);
			//show hide screen
			Context.getInstance().changeScreen(actionEvent, DashBoardView.getInstance());
		}
	}

	@Override
	public void prepareUI() {
	}
	
	@Override
	public void logout(ActionEvent actionEvent) {
	}
	
	@Override
	public void backDashBoard(ActionEvent actionEvent) {
	}
}
