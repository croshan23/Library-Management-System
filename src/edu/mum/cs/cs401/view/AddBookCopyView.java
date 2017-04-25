package edu.mum.cs.cs401.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AddBookCopyView extends View{
	
	/**
	 * This is the private constructor (Singleton Pattern).
	 */
	public AddBookCopyView() {
	}
	
	private static AddBookCopyView bookCopy = new AddBookCopyView();
	
	public static AddBookCopyView getInstance(){
		return bookCopy;
	}
	
	@Override
	public Scene getScene() {
		return super.getScene("AddBookCopy.fxml");
	}
	
	@Override
	protected Scene getScene(String resouce, double width, double height) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resouce));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root, 300, 100);
			scene.getStylesheets().add(getClass().getResource("mycss.css").toExternalForm());
			return scene;
		} catch (IOException e) {
		}
		return null;
	}
}
