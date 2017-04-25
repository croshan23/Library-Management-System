package edu.mum.cs.cs401.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.constant.ContextDataKey;
import edu.mum.cs.cs401.context.ApplicationDataContext;
import edu.mum.cs.cs401.context.Context;
import edu.mum.cs.cs401.dao.BookCopyDAO;
import edu.mum.cs.cs401.dao.impl.BookCopyDAOImpl;
import edu.mum.cs.cs401.dao.impl.BookDAOImpl;
import edu.mum.cs.cs401.dao.impl.PersonDAOImpl;
import edu.mum.cs.cs401.entity.AvailableStatus;
import edu.mum.cs.cs401.entity.Book;
import edu.mum.cs.cs401.entity.BookCopy;
import edu.mum.cs.cs401.entity.Person;
import edu.mum.cs.cs401.entity.Role;
import edu.mum.cs.cs401.view.AddBookCopyView;
import edu.mum.cs.cs401.view.AddBookView;
import edu.mum.cs.cs401.view.AddMemberView;
import edu.mum.cs.cs401.view.CheckoutView;
import edu.mum.cs.cs401.view.RecordView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashBoardController extends Controller {
	
	@FXML
	private TabPane tabPane;

	@FXML
	private Tab adminTab;
	
	@FXML
	private Tab libTab;
	
	@FXML
	private TextField searchMemberIdText;
	
	@FXML
	private TextField isbnSearchTextField;
	
	@FXML
	private TableView<Person> memberTableView;
	
	@FXML
	private TableColumn<Person, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Person, String> tableColumnFirstName;
	
	@FXML
	private TableColumn<Person, String> tableColumnLastName;
	
	@FXML
	private Button addBookCopyButton;
	
	@FXML
	private Button addBookButton;
	
	@FXML
	private Button addMemberButton;
	
	@FXML
	private Button checkoutButton;
	
	@FXML
	private Button recordButton;
	
	@FXML
	private TableView<Book> tableViewBook;
	
	@FXML
	private TableView<BookCopy> tableViewBookCopy;
	
	@FXML
	private TableColumn<Book, String> tableColumnISBN;
	
	@FXML
	private TableColumn<Book, String> tableColumnTitle;
	
	@FXML
	private TableColumn<Book, String> tableColumnDescription;
	
	@FXML
	private TableColumn<BookCopy, String> tableColumnBookCopy;
	
	@FXML
	private TableColumn<BookCopy, String> tableColumnAvailability;
	
	private BookCopyDAO bookCopyDAO = BookCopyDAOImpl.getInstance();
	
	@FXML
	TextField copyNumber;


	public void addBookCopy(ActionEvent actionEvent) throws IOException {

		BookCopy bookCopy = new BookCopy();
		Book activeBook = (Book) ApplicationDataContext.getInstance().get(ContextDataKey.ADD_BOOK_COPY_BOOK);
		
		bookCopy.setCopyNumber(copyNumber.getText());
		bookCopy.setIsbn(activeBook.getIsbn());
		bookCopy.setIsAvailable(AvailableStatus.Available);
		
		bookCopyDAO.addBookCopy(bookCopy);
		Stage popupStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
		popupStage.close();
	}
	
	@Override
	public void prepareUI() {
		super.prepareUI();
		List<Role> roles = Context.getInstance().getUser().getRoles();
		// init table
		tableColumnId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
		tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		tableColumnLastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		tableColumnISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		tableColumnTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
		tableColumnBookCopy.setCellValueFactory(new PropertyValueFactory<BookCopy, String>("copyNumber"));
		tableColumnAvailability.setCellValueFactory(new PropertyValueFactory<BookCopy, String>("isAvailable"));
		//bind table
		tableViewBook.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	List<BookCopy> bookCopies = BookCopyDAOImpl.getInstance().searchBookCopies(newSelection.getIsbn());
		    	ObservableList<BookCopy> data = FXCollections.observableArrayList(bookCopies);
		    	tableViewBookCopy.setItems(data);
		    } else {
		    	tableViewBookCopy.getItems().clear();
		    }
		});
		
		setAllBookToTable();
		setAllMemberToTable();
		if (!roles.contains(Role.ADMIN)) {
			addBookCopyButton.setDisable(true);
			addBookButton.setDisable(true);
			addMemberButton.setDisable(true);
		}
		if (!roles.contains(Role.LIBRARIAN)) {
			checkoutButton.setDisable(true);
			recordButton.setDisable(true);
		}
	}
	
	@Override
	public void backDashBoard(ActionEvent actionEvent) {
	}

	public void checkout(ActionEvent actionEvent) {
		Context.getInstance().changeScreen(actionEvent, CheckoutView.getInstance());
	}
	
	public void addMember(ActionEvent actionEvent) {
		Context.getInstance().changeScreen(actionEvent, AddMemberView.getInstance());
	}
	
	public void addBook(ActionEvent actionEvent) {
		Context.getInstance().changeScreen(actionEvent, AddBookView.getInstance());
	}
	
	public void searchMember(ActionEvent actionEvent) {
		String id = searchMemberIdText.getText();
		if (id.isEmpty()) {
			setAllMemberToTable();
		} else {
			Person search = PersonDAOImpl.getInstance().search(id, Role.MEMBER);
			ObservableList<Person> data = null;
			if (search != null) {
				List<Person> list = new ArrayList<Person>();
				list.add(search);
				data = FXCollections.observableArrayList(list );
			}
			memberTableView.setItems(data);
		}
	}
	
	public void checkoutSceneButton(ActionEvent actionEvent) {
		Person selectedItem = memberTableView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			ApplicationDataContext.getInstance().put(ContextDataKey.CHECKOUT_MEMBER, selectedItem);
			Context.getInstance().changeScreen(actionEvent, CheckoutView.getInstance());
		}
	}
	
	private void setAllMemberToTable() {
		List<Person> list = PersonDAOImpl.getInstance().getAll(Role.MEMBER);
		ObservableList<Person> data = FXCollections.observableArrayList(list);
		memberTableView.setItems(data);
	}
	
	private void setAllBookToTable() {
		List<Book> list = BookDAOImpl.getInstance().getAll();
		ObservableList<Book> data = FXCollections.observableArrayList(list);
		tableViewBook.setItems(data);
	}
	
	public void getRecords(ActionEvent actionEvent) {
		Person selectedItem = memberTableView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			ApplicationDataContext.getInstance().put(ContextDataKey.RECORD_MEMBER, selectedItem);
			Context.getInstance().changeScreen(actionEvent, RecordView.getInstance());
		}
	}
	
	public void addBookButton(ActionEvent actionEvent) {
		Context.getInstance().changeScreen(actionEvent, AddBookView.getInstance());
	}
	
	public void bookSearchButton(ActionEvent actionEvent) {
		String isbn = isbnSearchTextField.getText();
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
			tableViewBook.setItems(data);
		}
	}
	
	public void addBookCopyButton(ActionEvent actionEvent) {
		final Stage dialog = new Stage();
		Book selectedItem = tableViewBook.getSelectionModel().getSelectedItem();
		ApplicationDataContext.getInstance().put(ContextDataKey.ADD_BOOK_COPY_BOOK, selectedItem);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(AddBookCopyView.getInstance().getScene());
        dialog.show();
	}
	
}
