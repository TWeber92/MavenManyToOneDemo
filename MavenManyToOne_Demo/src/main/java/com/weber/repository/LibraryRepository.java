package com.weber.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.weber.model.Book;
import com.weber.model.Member;
import com.weber.utility.DatabaseConnector;

public class LibraryRepository {
	
	public String callingClass;
	
	public LibraryRepository() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        this.callingClass = stackTrace[2].getClassName(); // Get the class name of the caller
	}
	
	public static Connection connect(String call) throws IOException, Exception {
		DatabaseConnector connector = new DatabaseConnector();
		Connection connection = connector.getConnection(call);
		return connection;
	}
	
	public ResultSet selectMemberByISBN(String isbn) throws SQLException, Exception {
		String selectSql = "SELECT M.first_name, M.last_name, M.member_id FROM Members M JOIN Books B ON M.member_id = B.memberId WHERE B.isbn = ?";
		PreparedStatement preparedStatement = connect(callingClass).prepareStatement(selectSql); 
		preparedStatement.setString(1, isbn);
		return preparedStatement.executeQuery();
	}
	public int insertBook(Book book) throws SQLException, Exception {
		String insertSql = "INSERT INTO Books (title, author, isbn) VALUES (?, ?, ?)";
		PreparedStatement preparedStatement = connect(callingClass).prepareStatement(insertSql);
		preparedStatement.setString(1, book.getTitle());
		preparedStatement.setString(2, book.getAuthor());
		preparedStatement.setString(3, book.getIsbn());
		int result = preparedStatement.executeUpdate();
		preparedStatement.close();
		return result;
	}
	public ResultSet selectBooks() throws SQLException, Exception {
		String selectSql = "SELECT * FROM Books";
		Statement statement = connect(callingClass).createStatement();
		return statement.executeQuery(selectSql);
	}
	public int insertMember(Member member) throws SQLException, Exception {
		String insertSql = "INSERT INTO Members (first_name, last_name) VALUES (?, ?)";
		PreparedStatement preparedStatement = connect(callingClass).prepareStatement(insertSql);
		preparedStatement.setString(1, member.getFirstName());
		preparedStatement.setString(2, member.getLastName());
		int result = preparedStatement.executeUpdate();
		preparedStatement.close();
		return result;
		
	}
	public int assignMemberToBook(int memberId, String isbn) throws SQLException, Exception {
		String updateSql = "UPDATE Books SET memberId = ? WHERE isbn = ?";
		PreparedStatement preparedStatement = connect(callingClass).prepareStatement(updateSql);
		preparedStatement.setInt(1, memberId);
		preparedStatement.setString(2, isbn);
		int result = preparedStatement.executeUpdate();
		preparedStatement.close();
		return result;
	}
	public int updateBookAuthor(Book book) throws SQLException, Exception {
		String updateSql = "UPDATE Books SET author = ? WHERE id = ?";
		PreparedStatement preparedStatement = connect(callingClass).prepareStatement(updateSql);
		preparedStatement.setString(1, book.getAuthor());
		preparedStatement.setInt(2, book.getId());
		int result = preparedStatement.executeUpdate();
		preparedStatement.close();
		return result;
	}
	public int deleteBook(int id) throws SQLException, Exception {
		String deleteSql = "DELETE FROM Books WHERE id = ?";
		PreparedStatement preparedStatement = connect(callingClass).prepareStatement(deleteSql);
		preparedStatement.setInt(1, id);
		int result = preparedStatement.executeUpdate();
		preparedStatement.close();
		return result;
	}
}
