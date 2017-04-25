package edu.mum.cs.cs401.entity;

import java.util.List;

public class Book {

	private String title;
	
	private String isbn;
	
	private String description;
	
	private List<Author> authors;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Book() {
	}

	public Book(String title, String isbn, String description, List<Author> authors) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.description = description;
		this.authors = authors;
	}
	
}
