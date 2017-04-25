package edu.mum.cs.cs401.dao.impl;

import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.dao.BookDAO;
import edu.mum.cs.cs401.dao.DataAccess;
import edu.mum.cs.cs401.entity.Book;

public class BookDAOImpl implements BookDAO {
	private String cdr = System.getProperty("user.dir");
	private String bookJson = cdr + "/src/edu/mum/cs/cs401/dao/book.json";
	private static List<Book> list;

	private BookDAOImpl() {
		loadList();
	}

	private static BookDAOImpl bookDAO = new BookDAOImpl();

	public static BookDAOImpl getInstance() {
		return bookDAO;
	}

	@Override
	public List<Book> getAll() {
		return list;
	}

	public void loadList() {
		list = DataAccess.getBookList(bookJson);
	}

	@Override
	public void addBooks(List<Book> books) {
		if (list == null) {
			list = new ArrayList<Book>();
		}
		list.addAll(books);
		DataAccess.save(list, bookJson);
	}
	
	@Override
	public void addBook(Book book) {
		if (list == null) {
			list = new ArrayList<Book>();
		}
		boolean exist = false;
		for (Book b : list) {
			if (b.getIsbn().equals(book.getIsbn())) {
				exist = true;
			}
		}
		if (!exist) {
			list.add(book);
			DataAccess.save(list, bookJson);
		}
	}


	@Override
	public Book searchBook(String isbn) {
		for (Book book : list) {
			if (book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}

}
