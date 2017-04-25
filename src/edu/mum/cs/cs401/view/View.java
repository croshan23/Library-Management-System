package edu.mum.cs.cs401.view;

import java.io.IOException;

import edu.mum.cs.cs401.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class View {
	
	public final static double WIDTH = 535.0;
	
	public final static double HIEGHT = 500;
	
	public abstract Scene getScene();
	
	protected Scene getScene(String resouce) {
		return getScene(resouce, WIDTH, HIEGHT);
	}
	
	protected Scene getScene(String resouce, double width, double height) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resouce));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root, width, height);
			scene.getStylesheets().add(getClass().getResource("mycss.css").toExternalForm());
			Controller myController = (Controller) fxmlLoader.getController();
			myController.prepareUI();
			return scene;
		} catch (IOException e) {
		}
		return null;
	}
}
