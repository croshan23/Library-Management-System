package edu.mum.cs.cs401.controller;

import edu.mum.cs.cs401.context.Context;
import edu.mum.cs.cs401.view.DashBoardView;
import edu.mum.cs.cs401.view.LoginView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public abstract class Controller {
	
	@FXML
	private Label userWelcomeLabel;
	
	public void prepareUI() {
		String username = Context.getInstance().getUser().getFirstName();
		userWelcomeLabel.setText("Login as " + username);
	}
	
	public void logout(ActionEvent actionEvent) {
		Context.getInstance().setUser(null);
		// change to login screen
		Context.getInstance().changeScreen(actionEvent, LoginView.getInstance());
	}
	
	public void backDashBoard(ActionEvent actionEvent) {
		Context.getInstance().changeScreen(actionEvent, DashBoardView.getInstance());
	}
}
