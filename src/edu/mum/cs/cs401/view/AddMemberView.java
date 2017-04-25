package edu.mum.cs.cs401.view;

import javafx.scene.Scene;

public class AddMemberView extends View {

	/**
	 * This is the private constructor (Singleton Pattern).
	 */
	private AddMemberView() {
	}

	private static AddMemberView addMemberView = new AddMemberView();

	public static AddMemberView getInstance() {
		return addMemberView;
	}

	@Override
	public Scene getScene() {
		return super.getScene("AddMember.fxml", WIDTH, HIEGHT);
	}

}
