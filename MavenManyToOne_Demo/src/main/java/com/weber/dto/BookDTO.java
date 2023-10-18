package com.weber.dto;

public class BookDTO {
	
	private int id;
	private String title;
	private String author;
	private String isbn;
	
	public BookDTO() {
		
	}
	
	public BookDTO(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
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

	@Override
	public String toString() {
		return "BookDTO [title=" + title + ", author=" + author + ", isbn=" + isbn + "]";
	}
	
	

}
