package edu.mum.cs.cs401.view;

import javafx.scene.Scene;

public class RecordView extends View {
	
	private static RecordView view = new RecordView();
	
	public static RecordView getInstance() {
		return view;
	}
	
	private RecordView() {
	}

	@Override
	public Scene getScene() {
		return super.getScene("Record.fxml");
	}

}
