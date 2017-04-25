package edu.mum.cs.cs401.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.constant.ContextDataKey;
import edu.mum.cs.cs401.context.ApplicationDataContext;
import edu.mum.cs.cs401.context.Context;
import edu.mum.cs.cs401.dao.impl.BookCopyDAOImpl;
import edu.mum.cs.cs401.dao.impl.BookDAOImpl;
import edu.mum.cs.cs401.dao.impl.RecordDAOImpl;
import edu.mum.cs.cs401.entity.AvailableStatus;
import edu.mum.cs.cs401.entity.Book;
import edu.mum.cs.cs401.entity.BookCopy;
import edu.mum.cs.cs401.entity.Person;
import edu.mum.cs.cs401.entity.Record;
import edu.mum.cs.cs401.view.DashBoardView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CheckoutController extends Controller {
	
	@FXML
	private Label personCheckoutLabel;
	
	@FXML
	private TableView<Book> bookTableView;
	
	@FXML
	private TableView<BookCopy> bookCopyTableView;
	
	@FXML
	private TableColumn<Book, String> tableColumnIsbn;
	
	@FXML
	private TableColumn<Book, String> tableColumTitle;
	
	@FXML
	private TableColumn<Book, String> tableColumnDescription;
	
	@FXML
	private TextField searchIsbnTextField;
	
	@FXML
	private TableColumn<BookCopy, String> tableColumCopynumber;
	
	@FXML
	private TableColumn<BookCopy, Boolean> tableColumnAvailability;
	
	@FXML
	private TextField borrowDaysTextField;

	@Override
	public void prepareUI() {
		super.prepareUI();
		//set label
		Person person = (Person) ApplicationDataContext.getInstance().get(ContextDataKey.CHECKOUT_MEMBER);
		personCheckoutLabel.setText("Checking out for " + person.getFirstName() + " id: " + person.getId());
		
		// setup table
		tableColumnIsbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		tableColumTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
		setAllBookToTable();
		tableColumCopynumber.setCellValueFactory(new PropertyValueFactory<BookCopy, String>("copyNumber"));
		tableColumnAvailability.setCellValueFactory(new PropertyValueFactory<BookCopy, Boolean>("isAvailable"));
		
		//bind table
		bookTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	List<BookCopy> bookCopies = BookCopyDAOImpl.getInstance().searchBookCopies(newSelection.getIsbn());
		    	ObservableList<BookCopy> data = FXCollections.observableArrayList(bookCopies);
		    	bookCopyTableView.setItems(data);
		    } else {
		    	bookCopyTableView.getItems().clear();
		    }
		});
	}
	
	public void searchIsbnButton(ActionEvent actionEvent) {
		String isbn = searchIsbnTextField.getText();
		if (isbn.isEmpty()) {
			setAllBookToTable();
		} else {
			Book search = BookDAOImpl.getInstance().searchBook(isbn);
			ObservableList<Book> data = null;
			if (search != null) {
				List<Book> list = new ArrayList<Book>();
				list.add(search);
				data = FXCollections.observableArrayList(list );
			}
			bookTableView.setItems(data);
		}
	}

	public void checkoutButton(ActionEvent actionEvent) {
		BookCopy selectedItem = bookCopyTableView.getSelectionModel().getSelectedItem();
		String days = borrowDaysTextField.getText();
		int dayInt = 0;
		try {
			dayInt = Integer.parseInt(days);
		} catch (Exception e) {
		}
		if (selectedItem != null && dayInt > 0 && selectedItem.getIsAvailable().equals(AvailableStatus.Available)) {
			Person person = (Person) ApplicationDataContext.getInstance().get(ContextDataKey.CHECKOUT_MEMBER);
			Record record = new Record(LocalDate.now(), dayInt, person.getId(), selectedItem.getCopyNumber());
			List<Record> records = new ArrayList<Record>();
			records.add(record);
			RecordDAOImpl.getInstance().addRecords(records);
			BookCopyDAOImpl.getInstance().updateBookCopyStatus(selectedItem.getCopyNumber(), AvailableStatus.Unavailable);
			Context.getInstance().changeScreen(actionEvent, DashBoardView.getInstance());
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Invalid input");
			alert.setHeaderText("Request invalid");
			alert.setContentText("Please check again your input");
			alert.showAndWait().ifPresent(rs -> {
			});
		}
	}

	private void setAllBookToTable() {
		List<Book> list = BookDAOImpl.getInstance().getAll();
		ObservableList<Book> data = FXCollections.observableArrayList(list);
		bookTableView.setItems(data);
	}
}
