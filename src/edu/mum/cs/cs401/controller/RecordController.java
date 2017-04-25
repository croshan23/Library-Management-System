package edu.mum.cs.cs401.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.constant.ContextDataKey;
import edu.mum.cs.cs401.context.ApplicationDataContext;
import edu.mum.cs.cs401.dao.impl.BookCopyDAOImpl;
import edu.mum.cs.cs401.dao.impl.BookDAOImpl;
import edu.mum.cs.cs401.dao.impl.RecordDAOImpl;
import edu.mum.cs.cs401.entity.Book;
import edu.mum.cs.cs401.entity.BookCopy;
import edu.mum.cs.cs401.entity.Person;
import edu.mum.cs.cs401.entity.Record;
import edu.mum.cs.cs401.entity.RecordPrintEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RecordController extends Controller {
	
	@FXML
	private TableView<RecordPrintEntity> recordTableView;
	
	@FXML
	private TableColumn<RecordPrintEntity, LocalDate> tableColumnCheckoutDate;
	
	@FXML
	private TableColumn<RecordPrintEntity, String> tableColumCopyNumber;
	
	@FXML
	private TableColumn<RecordPrintEntity, String> tableColumISBN;
	
	@FXML
	private TableColumn<RecordPrintEntity, String> tableColumTitle;
	
	@FXML
	private Label personRecordLabel;

	@Override
	public void prepareUI() {
		super.prepareUI();
		//set label
		Person person = (Person) ApplicationDataContext.getInstance().get(ContextDataKey.RECORD_MEMBER);
		personRecordLabel.setText("Record of " + person.getFirstName() + " id: " + person.getId());
		
		tableColumISBN.setCellValueFactory(new PropertyValueFactory<RecordPrintEntity, String>("isbn"));
		tableColumTitle.setCellValueFactory(new PropertyValueFactory<RecordPrintEntity, String>("title"));
		tableColumnCheckoutDate.setCellValueFactory(new PropertyValueFactory<RecordPrintEntity, LocalDate>("checkoutDate"));
		tableColumCopyNumber.setCellValueFactory(new PropertyValueFactory<RecordPrintEntity, String>("copyNumber"));
		
		List<Record> searchRecordsByMember = RecordDAOImpl.getInstance().searchRecordsByMember(person.getId());
		List<RecordPrintEntity> list = new ArrayList<RecordPrintEntity>();
		for (Record rec : searchRecordsByMember) {
			BookCopy bookCopy = BookCopyDAOImpl.getInstance().searchBookCopy(rec.getCopyNumber());
			Book searchBook = BookDAOImpl.getInstance().searchBook(bookCopy.getIsbn());
			RecordPrintEntity entity = new RecordPrintEntity(rec.getCheckoutDate(), rec.getCopyNumber(), searchBook.getIsbn(), searchBook.getTitle());
			list.add(entity);
		}
		ObservableList<RecordPrintEntity> data = FXCollections.observableArrayList(list);
		recordTableView.setItems(data);
	}
}
