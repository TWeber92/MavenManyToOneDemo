package com.weber.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weber.dto.BookDTO;
import com.weber.dto.MemberDTO;
import com.weber.model.Book;
import com.weber.model.Member;
import com.weber.repository.LibraryRepository;
import com.weber.utility.Validator;

public class LibraryServiceImpl implements LibraryService{
	
	private LibraryRepository repository;
	
	public LibraryServiceImpl() {
		this.repository = new LibraryRepository();
	}
	
	public List<MemberDTO> selectMemberByISBN(String isbn) throws SQLException, Exception {		
		Validator.validateBookData(new BookDTO("billy", "jean", isbn));
		ResultSet resultSet = repository.selectMemberByISBN(isbn);
		List<MemberDTO> members = new ArrayList<MemberDTO>();
		while (resultSet.next()) {
			MemberDTO member = new MemberDTO();
			member.setFirstName(resultSet.getString("first_name"));
			member.setLastName(resultSet.getString("last_name"));
			member.setMemberId(resultSet.getString("member_id"));
			members.add(member);
		}
		resultSet.close();
		return members;
	}
	
	public int insertMember(MemberDTO memberDTO) throws SQLException, Exception {
		Validator.validateMemberData(memberDTO);
		Member member = new Member();
		member.setFirstName(memberDTO.getFirstName());
		member.setLastName(memberDTO.getLastName());
		return repository.insertMember(member);
	}

	public int insertBook(BookDTO bookDTO) throws SQLException, Exception {
		Validator.validateBookData(bookDTO);
		Book book = new Book();
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setIsbn(bookDTO.getIsbn());
		return repository.insertBook(book);
	}

	public List<BookDTO> selectBooks() throws SQLException, Exception {
		ResultSet resultSet = repository.selectBooks();
		List<BookDTO> books = new ArrayList<BookDTO>();
		while (resultSet.next()) {
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			String isbn = resultSet.getString("isbn");
			BookDTO book =  new BookDTO(title, author, isbn);
			books.add(book);
		}
		resultSet.close();
		return books;
	}

	public int assignMemberToBook(int memberId, String isbn) throws SQLException, Exception {
		Validator.validateBookData(new BookDTO("billy", "jean", isbn));
		return repository.assignMemberToBook(memberId, isbn);
	}

	public int updateBookAuthor(BookDTO bookDTO) throws SQLException, Exception {
		Validator.validateBookData(bookDTO);
		Book book = new Book();
		book.setId(bookDTO.getId());
		book.setAuthor(bookDTO.getAuthor());
		return repository.updateBookAuthor(book);
	}

	public int deleteBook(int id) throws SQLException, Exception {
		return repository.deleteBook(id);
	}

}
