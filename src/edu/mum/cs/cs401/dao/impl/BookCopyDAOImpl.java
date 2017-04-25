package edu.mum.cs.cs401.dao.impl;

import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.dao.BookCopyDAO;
import edu.mum.cs.cs401.dao.DataAccess;
import edu.mum.cs.cs401.entity.AvailableStatus;
import edu.mum.cs.cs401.entity.BookCopy;

public class BookCopyDAOImpl implements BookCopyDAO{
	
	private String cdr = System.getProperty("user.dir");
	private String bookJson =cdr + "/src/edu/mum/cs/cs401/dao/bookcopy.json";
	private static List<BookCopy> list;
	
	private BookCopyDAOImpl(){
		loadList();
	}
	
	private static BookCopyDAOImpl bookCopyDAO = new BookCopyDAOImpl();
	
	public static BookCopyDAOImpl getInstance() {
		return bookCopyDAO;
	}

	@Override
	public List<BookCopy> getAll() {
		return list;
	}

	@Override
	public void addBookCopies(List<BookCopy> bookcopy) {
		if (list == null) {
			list = new ArrayList<BookCopy>();
		} 
		list.addAll(bookcopy);
		DataAccess.save(list, bookJson);
	}
	
	@Override
	public void addBookCopy(BookCopy bookcopy) {
		if (list == null) {
			list = new ArrayList<BookCopy>();
		} 
		list.add(bookcopy);
		DataAccess.save(list, bookJson);
	}

	@Override
	public List<BookCopy> searchBookCopies(String isbn) {
		List<BookCopy> listBook = new ArrayList<BookCopy>();
		for (BookCopy bc : list) {
			if (bc.getIsbn().equals(isbn)) {
				listBook.add(bc);
			}
		}
		return listBook;
	}

	public void loadList(){
		list = DataAccess.getBookCopyList(bookJson);
	}

	@Override
	public void updateBookCopyStatus(String copyNumber, AvailableStatus status) {
		for (BookCopy bc : list) {
			if (bc.getCopyNumber().equals(copyNumber)) {
				bc.setIsAvailable(status);
			}
		}
		DataAccess.save(list, bookJson);
	}

	@Override
	public BookCopy searchBookCopy(String bookCopy) {
		for (BookCopy bc : list) {
			if (bc.getCopyNumber().equals(bookCopy)) {
				return bc;
			}
		}
		return null;
	}
}
