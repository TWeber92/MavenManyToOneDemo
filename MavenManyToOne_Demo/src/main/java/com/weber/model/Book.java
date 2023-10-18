package com.weber.model;

public class Book {
	private int id;
	private String title;
	private String author;
	private String isbn;
	private int memberId;
	
	public Book() {
		
	}
	
	public Book(String title, String author, String isbn, int memberId) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.memberId = memberId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", isbn=" + isbn + ", memberId=" + memberId + "]";
	}
	
}
