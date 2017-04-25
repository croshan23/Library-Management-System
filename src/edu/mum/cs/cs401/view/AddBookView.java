package edu.mum.cs.cs401.view;

import javafx.scene.Scene;

public class AddBookView extends View{
	
	/**
	 * This is the private constructor (Singleton Pattern).
	 */
	public AddBookView() {
	}
	
	private static AddBookView bookCopy = new AddBookView();
	
	public static AddBookView getInstance(){
		return bookCopy;
	}
	
	@Override
	public Scene getScene() {
		return super.getScene("AddBookView.fxml");
	}
}
