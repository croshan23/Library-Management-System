package edu.mum.cs.cs401.view;

import javafx.scene.Scene;

public class DashBoardView extends View{
	
	private DashBoardView() {
		
	}
	
	private static DashBoardView dashBoard = new DashBoardView();
	
	public static DashBoardView getInstance() {
		return dashBoard; 
	}
	
	@Override
	public Scene getScene() {
		return super.getScene("DashBoard.fxml", 900, 500);
	}

}
