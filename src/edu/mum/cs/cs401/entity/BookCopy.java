package edu.mum.cs.cs401.entity;

public class BookCopy {

	private String copyNumber;
	
	private AvailableStatus isAvailable;
	
	private String isbn;
	
	public String getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}
	
	public BookCopy(String copyNumber, AvailableStatus isAvailable, String isbn) {
		super();
		this.setIsAvailable(isAvailable);
		this.copyNumber = copyNumber;
		this.isbn = isbn;
	}

	public BookCopy() {
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public AvailableStatus getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(AvailableStatus isAvailable) {
		this.isAvailable = isAvailable;
	}
}
