package edu.mum.cs.cs401.view;

import javafx.scene.Scene;

public class CheckoutView extends View {
	
	private static CheckoutView view = new CheckoutView();
	
	public static CheckoutView getInstance() {
		return view;
	}
	
	private CheckoutView() {
	}

	@Override
	public Scene getScene() {
		return super.getScene("Checkout.fxml");
	}

}
