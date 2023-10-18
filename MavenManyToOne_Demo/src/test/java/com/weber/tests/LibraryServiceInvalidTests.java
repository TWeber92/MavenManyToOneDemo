package com.weber.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Test;

import com.weber.dto.BookDTO;
import com.weber.dto.MemberDTO;
import com.weber.exception.WeberLibraryException;
import com.weber.service.LibraryService;
import com.weber.service.LibraryServiceImpl;

public class LibraryServiceInvalidTests {

	private static ResourceBundle message = ResourceBundle.getBundle("err-messages");
	
	LibraryService libraryService = new LibraryServiceImpl();

	@Test
	public void testInvalidFirstName() {
		List<MemberDTO> members = Arrays.asList(
		        new MemberDTO("*", "Doe"),new MemberDTO("", "Doe")
		    );
		members.forEach(member -> {
			WeberLibraryException exception = assertThrows(WeberLibraryException.class, 
					() -> libraryService.insertMember(member));
			assertEquals(message.getString("invalid.first"), exception.getMessage());
		});
	}
	@Test
	public void testInvalidLastName() {
		List<MemberDTO> members = Arrays.asList(
		        new MemberDTO("John", "*"),new MemberDTO("John", "")
		    );
		members.forEach(member -> {
			WeberLibraryException exception = assertThrows(WeberLibraryException.class, 
					() -> libraryService.insertMember(member));
			assertEquals(message.getString("invalid.last"), exception.getMessage());
		});
	}
	@Test
	public void testInvalidIsbn() {
		List<String> isbns = Arrays.asList("", "123");
		isbns.forEach(isbn -> {
			WeberLibraryException exception = assertThrows(WeberLibraryException.class, 
					() -> libraryService.selectMemberByISBN(isbn));
			assertEquals(message.getString("invalid.isbn"), exception.getMessage());
		});
	}
	@Test
	public void testInvalidAuthor() {
		List<BookDTO> books = Arrays.asList(
				new BookDTO("Harry Potter", "*", "1234567890"),
				new BookDTO("Harry Potter", "", "1234567890"));
		books.forEach(book -> {
			WeberLibraryException exception = assertThrows(WeberLibraryException.class, 
					() -> libraryService.insertBook(book));
			assertEquals(message.getString("invalid.author"), exception.getMessage());
		});
	}
	@Test
	public void testInvalidTitle() {
		List<BookDTO> books = Arrays.asList(
				new BookDTO("*", "J.K. Rowling", "1234567890"),
				new BookDTO("", "J.K. Rowling", "1234567890"));
		books.forEach(book -> {
			WeberLibraryException exception = assertThrows(WeberLibraryException.class,
					() -> libraryService.insertBook(book));
			assertEquals(message.getString("invalid.title"), exception.getMessage());
		});
	}
}
