package com.weber.dto;

public class BookDTO {

	private int member_id;
	private String title;
	private String author;
	private String isbn;

	public BookDTO() {

	}

	public BookDTO(int member_id, String title, String author, String isbn) {
		this.member_id = member_id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}

	public int getMemberId() {
		return member_id;
	}

	public void setMemberId(int member_id) {
		this.member_id = member_id;
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
