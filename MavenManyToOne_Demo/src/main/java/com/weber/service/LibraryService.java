package com.weber.service;

import java.sql.SQLException;
import java.util.List;

import com.weber.dto.BookDTO;
import com.weber.dto.MemberDTO;

public interface LibraryService {
	public List<MemberDTO> selectMemberByISBN(String isbn) throws SQLException, Exception;
	public int insertMember(MemberDTO member) throws SQLException, Exception;
	public int insertBook(BookDTO book) throws SQLException, Exception;
	public List<BookDTO> selectBooks() throws SQLException, Exception;
	public int assignMemberToBook(int memberId, String isbn) throws SQLException, Exception;
	public int updateBookAuthor(BookDTO book) throws SQLException, Exception;
	public int deleteBook(int id) throws SQLException, Exception;
}
