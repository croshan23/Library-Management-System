package edu.mum.cs.cs401.view;

import javafx.scene.Scene;

public class LoginView extends View {
	private LoginView() {
		
	}
	private static LoginView loginView = new LoginView();
	
	public static LoginView getInstance() {
		return loginView;
	}
	
	@Override
	public Scene getScene() {
		return super.getScene("Login.fxml", 375.0, 305.0);
	}

}
