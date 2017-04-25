package edu.mum.cs.cs401.dao;

import java.util.List;

import edu.mum.cs.cs401.entity.Book;

public interface BookDAO {

	public List<Book> getAll();
	
	public void addBooks(List<Book> books);
	
	public void addBook(Book book);
	
	public Book searchBook(String isbn);
	
}
