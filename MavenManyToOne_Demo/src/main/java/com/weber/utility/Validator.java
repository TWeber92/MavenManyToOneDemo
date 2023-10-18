package com.weber.utility;

import java.util.ResourceBundle;

import com.weber.dto.BookDTO;
import com.weber.dto.MemberDTO;
import com.weber.exception.WeberLibraryException;

public class Validator {
	
	private static ResourceBundle message = ResourceBundle.getBundle("err-messages");

	public static void validateBookData(BookDTO book) throws WeberLibraryException {
		String errorStatus = 
				!validateBookIsbn(book.getIsbn()) ? Validator.message.getString("invalid.isbn")
				: !validateAuthorName(book.getAuthor()) ? Validator.message.getString("invalid.author")
				: ! validateBookTitle(book.getTitle()) ? Validator.message.getString("invalid.title")
				: null;
		if (errorStatus != null) throw new WeberLibraryException(errorStatus);
	}
	public static void validateMemberData(MemberDTO member) throws WeberLibraryException {
		String errorStatus = 
				!validateMemberFirstName(member.getFirstName()) ? Validator.message.getString("invalid.first")
				:!validateMemberLastName(member.getLastName()) ? Validator.message.getString("invalid.last")
				:null;
		if (errorStatus != null) throw new WeberLibraryException(errorStatus);
	}
	
	private static boolean validateMemberFirstName(String firstName) {
		if (firstName.isBlank()) return false;
		String regexFirst = "^(?!.*\\s)[A-Za-z]+$";
		return firstName.matches(regexFirst);
	}
	private static boolean validateMemberLastName(String lastName) {
		if (lastName.isBlank()) return false;
		String regexLast = "^[A-Za-z]+([-'][A-Za-z]+)*$";
		return lastName.matches(regexLast);
	}
	private static boolean validateBookTitle(String title) {
		if (title.isBlank()) return false;
		String regexTitle = "^[A-Za-z0-9\\s.'-]+$";
		return title.matches(regexTitle);
	}

	private static boolean validateAuthorName(String author) {
		if (author.isBlank()) return false;
		String regexAuthor = "^[A-Za-z\\s.'-]+$";
		return author.matches(regexAuthor);
	}

	public static boolean validateBookIsbn(String isbn) throws WeberLibraryException {
		if (isbn.isBlank()) return false;
		String regex = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]{10,17}$";
		return isbn.matches(regex);
	}
}
