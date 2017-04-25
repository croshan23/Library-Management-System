package edu.mum.cs.cs401.entity;

import java.time.LocalDate;

public class RecordPrintEntity {

	private LocalDate checkoutDate;
	
	private String copyNumber;
	
	private String isbn;
	
	private String title;

	public RecordPrintEntity(LocalDate checkoutDate, String copyNumber, String isbn, String title) {
		this.checkoutDate = checkoutDate;
		this.copyNumber = copyNumber;
		this.isbn = isbn;
		this.title = title;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
