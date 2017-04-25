package edu.mum.cs.cs401;

import edu.mum.cs.cs401.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Library System");
		primaryStage.setResizable(false);
		primaryStage.setScene(LoginView.getInstance().getScene());
		primaryStage.show();
	}
}
