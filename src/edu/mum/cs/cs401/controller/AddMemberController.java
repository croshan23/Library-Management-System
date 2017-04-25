package edu.mum.cs.cs401.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.context.Context;
import edu.mum.cs.cs401.dao.PersonDAO;
import edu.mum.cs.cs401.dao.impl.PersonDAOImpl;
import edu.mum.cs.cs401.entity.Address;
import edu.mum.cs.cs401.entity.Person;
import edu.mum.cs.cs401.entity.Role;
import edu.mum.cs.cs401.view.DashBoardView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AddMemberController extends Controller {

	private PersonDAO personDA = PersonDAOImpl.getInstance();
	
	@FXML
	TextField firstname;

	@FXML
	TextField lastnname;

	@FXML
	TextField phoneNumber;

	@FXML
	TextField street;

	@FXML
	TextField city;

	@FXML
	ComboBox<String> state;

	@FXML
	TextField zipCode;

	@FXML
	Label messageLabel;

	@FXML
	Pane inputsPane;

	@FXML
	TextField memberId;

	public void adjustItems() {
		state.getItems().addAll("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
				"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
				"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
				"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
				"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
				"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
				"West Virginia", "Wisconsin", "Wyoming");
	}

	public void saveNewMember(ActionEvent actionEvent) throws IOException {
		if (validInputs()) {
			Person member = new Person();
			member.setId(Integer.parseInt(memberId.getText()));
			member.setFirstName(firstname.getText());
			member.setLastName(lastnname.getText());

			List<String> phoneList = new ArrayList<>();
			phoneList.add(phoneNumber.getText());
			member.setPhone(phoneList);
			List<Address> addressList = new ArrayList<>();
			addressList.add(new Address(street.getText(), city.getText(), state.getValue(),
					Integer.parseInt(zipCode.getText())));
			member.setAddresses(addressList);
			List<Role> roles = new ArrayList<Role>();
			roles.add(Role.MEMBER);
			member.setRoles(roles);
			personDA.addPerson(member);
			Context.getInstance().changeScreen(actionEvent, DashBoardView.getInstance());
		}
	}

	private boolean validInputs() {
		boolean valid = true;
		messageLabel.setVisible(!valid);
		if (firstname.getText().isEmpty() || lastnname.getText().isEmpty() || phoneNumber.getText().isEmpty()
				|| street.getText().isEmpty() || city.getText().isEmpty() || state.getValue() == null
				|| zipCode.getText().isEmpty()) {
			messageLabel.setText("Please enter mandatory fields!");
			messageLabel.setVisible(true);
			valid = false;
		}
		if (!phoneNumber.getText().isEmpty()
				&& !phoneNumber.getText().matches("^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$")) {
			messageLabel.setText("Phone number is wrong! You can use 0-9 and it must be 10 digits.");
			messageLabel.setVisible(true);
			valid = false;
		}

		if (!zipCode.getText().isEmpty() && (!zipCode.getText().matches("[0-9]+") || zipCode.getText().length() != 5)) {
			messageLabel.setText("Zip Code is wrong! You can use 0-9 and it must be 5 digits.");
			messageLabel.setVisible(true);
			valid = false;
		}
		return valid;
	}

}
