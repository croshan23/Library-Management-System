package edu.mum.cs.cs401.entity;

import java.time.LocalDate;

public class Record {

	private LocalDate checkoutDate;
	
	private int borrowDays;
	
	private int personId;
	
	private String copyNumber;

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public int getBorrowDays() {
		return borrowDays;
	}

	public void setBorrowDays(int borrowDays) {
		this.borrowDays = borrowDays;
	}

	public Record(LocalDate checkoutDate, int borrowDays, int personId, String copyNumber) {
		this.checkoutDate = checkoutDate;
		this.borrowDays = borrowDays;
		this.personId = personId;
		this.copyNumber = copyNumber;
	}
	
	public Record() {
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}


}
