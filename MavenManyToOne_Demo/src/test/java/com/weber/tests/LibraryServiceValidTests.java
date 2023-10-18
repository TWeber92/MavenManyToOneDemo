package com.weber.tests;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.weber.model.Book;
import com.weber.model.Member;
import com.weber.repository.LibraryRepository;

public class LibraryServiceValidTests {
	
	LibraryRepository repository;
	Connection connection;
	Statement statement;
	
	
	@Before
	public void setup() throws Exception {
		repository = new LibraryRepository();
		connection = LibraryRepository.connect(repository.callingClass);
		initializeInMemoryDatabase();
	}

	private void initializeInMemoryDatabase() throws Exception {


	    statement = connection.createStatement();

	    // Create tables and insert test data
	    statement.executeUpdate("CREATE TABLE Members (" +
	        "member_id INT AUTO_INCREMENT PRIMARY KEY, " +
	        "first_name VARCHAR(255), " +
	        "last_name VARCHAR(255))");

	    statement.executeUpdate("CREATE TABLE Books (" +
	        "id INT AUTO_INCREMENT PRIMARY KEY, " +
	        "title VARCHAR(255), " +
	        "author VARCHAR(255), " +
	        "isbn VARCHAR(255), " +
	        "memberId INT, " +
	        "FOREIGN KEY (memberId) REFERENCES Members(member_id))");

	    statement.executeUpdate("INSERT INTO Members (first_name, last_name) VALUES " +
	        "('John', 'Doe'), " +
	        "('Jane', 'Smith')");

	    statement.executeUpdate("INSERT INTO Books (title, author, isbn, memberId) VALUES " +
	        "('Harry Potter', 'J.K. Rowling', '1234567890', 2)");  // Assuming memberId 1 corresponds to 'John' in Members table
	    
	    
	}

	
	@Test
	public void testSelectMemberByIsbn() throws SQLException, Exception {
		ResultSet members = repository.selectMemberByISBN("1234567890");
		while(members.next()) {
			String firstName = members.getString("first_name");
			String lastName = members.getString("last_name");
			String memberId = members.getString("member_id");
			assertEquals("Jane", firstName);
			assertEquals("Smith", lastName);
			assertEquals("2", memberId);
		}
	}
	
	@Test
	public void testSelectBooks() throws SQLException, Exception {
		ResultSet books = repository.selectBooks();
		while(books.next()) {
			String title = books.getString("title");
			String author = books.getString("author");
			String isbn = books.getString("isbn");
			int memberId = books.getInt("memberId");
			int bookId = books.getInt("id");
			assertEquals("Harry Potter", title);
			assertEquals("J.K. Rowling", author);
			assertEquals("1234567890", isbn);
			assertEquals(2, memberId);
			assertEquals(1, bookId);
		}
	}
	
	@Test
	public void testInsertMember() throws SQLException, Exception {
		Member member = new Member();
		member.setFirstName("Tad");
		member.setLastName("Weber");
		int result = repository.insertMember(member);
		assertEquals(1, result);
	}
	@Test
	public void testInsertBook() throws SQLException, Exception {
		Book book = new Book();
		book.setTitle("Lord Of The Rings");
		book.setAuthor("J.R.R. Tolkien");
		book.setIsbn("2345678901");
		int result = repository.insertBook(book);
		assertEquals(1, result);
	}
	
	@Test
	public void testAssignBookToMember() throws SQLException, Exception {
		int memberId = 1;
		String isbn = "1234567890";
		int result = repository.assignMemberToBook(memberId, isbn);
		assertEquals(1, result);
	}
	
	@Test 
	public void testUpdateBook() throws SQLException, Exception {
		Book book = new Book();
		book.setId(1);
		book.setTitle("Lord Of The Rings");
		book.setAuthor("JK Rowling");
		book.setIsbn("2345678901");
		int result = repository.updateBookAuthor(book);
		assertEquals(1, result);
	}
	
	@Test
	public void testDeleteBook() throws SQLException, Exception {
		int id = 1;
		int result = repository.deleteBook(id);
		assertEquals(1, result);
	}
	
	@After
	public void tearDown() throws SQLException {
		statement.executeUpdate("DROP TABLE Books");
		statement.executeUpdate("DROP TABLE Members");
		statement.close();
		connection.close();
	}
	
	
}