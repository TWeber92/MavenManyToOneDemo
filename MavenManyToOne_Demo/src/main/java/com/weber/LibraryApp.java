package com.weber;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.logging.Logger;
import com.weber.controller.ResponseHandler;
import com.weber.dto.BookDTO;
import com.weber.dto.MemberDTO;
import com.weber.exception.WeberLibraryException;
import com.weber.service.LibraryService;
import com.weber.service.LibraryServiceImpl;

public class LibraryApp {

	private final LibraryService libraryService;
	private static final Logger logger = Logger.getLogger(LibraryApp.class.getName());
	
	public LibraryApp() {
		this.libraryService = new LibraryServiceImpl();
	}
	

	public static void main(String[] args) throws Exception {
		
		// Create an HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Create connection to Service Interface
        LibraryService libraryService = new LibraryServiceImpl();

        // Create a context for handling requests
        server.createContext("/api/library", new ResponseHandler(libraryService));

        // Start the server
        server.start();
        System.out.println("Library App is Running");
		
		
//			selectMembersByISBN();
//			assignBookToMember();
//			insertMember();
//			insertBook();
//			selectBooks();
//			updateBook();
//			deleteBook();
	}

	// INSERT
//	public static void insertMember() throws WeberLibraryException {
//		try {
//			MemberDTO member = new MemberDTO("Tad", "Weber");
//			int rowsInserted = libraryService.insertMember(member);
//			if (rowsInserted > 0) {
//				logger.info("Member was successfully inserted!");
//			} else {
//				logger.warning("Member insert was unsuccessful!");
//			}
//
//		} catch (Exception e) {
//			logger.severe("An error has occured: " + e.getMessage());
//			throw new WeberLibraryException(e.getMessage());
//		}
//	}
//
//	public static void insertBook() throws WeberLibraryException {
//		try {
//			BookDTO book = new BookDTO();
//			book.setTitle("The [Great] Adventure");
//			book.setAuthor("J.R.R. Tolkien");
//			book.setIsbn("2345678901");
//
//			int result = libraryService().insertBook(book);
//			if (result > 0) {
//				logger.info("Book has been inserted successfully");
//			} else {
//				logger.warning("Book was not inserted successfully");
//			}
//		} catch (Exception e) {
//			logger.severe("An error has occurred: " + e.getMessage());
//			throw new WeberLibraryException(e.getMessage());
//		}
//	}
//
//	// SELECT
//	public static void selectMembersByISBN() throws WeberLibraryException {
//		try {
//			String isbn = "1234567890";
//			List<MemberDTO> members = libraryService().selectMemberByISBN(isbn);
//			if (members.isEmpty()) {
//				logger.warning("There were no members retrieved");
//			} else {
//				for (MemberDTO member : members) {
//					logger.info(member.toString());
//				}
//			}
//		} catch (Exception e) {
//			logger.severe("An error has occured: " + e.getMessage());
//			throw new WeberLibraryException(e.getMessage());
//		}
//	}
//
//	public static void selectBooks() throws WeberLibraryException {
//		try {
//			List<BookDTO> books = libraryService().selectBooks();
//			if (books.isEmpty()) {
//				logger.warning("No books were retrieved");
//			} else {
//				for (BookDTO book : books) {
//					logger.info(book.toString());
//				}
//			}
//
//		} catch (Exception e) {
//			logger.severe("An error has occurred: " + e.getMessage());
//			throw new WeberLibraryException(e.getMessage());
//		}
//	}
//
//	// UPDATE
//	public static void assignBookToMember() throws WeberLibraryException {
//		try {
//			int id = 2;
//			String isbn = "2345678901";
//			int rowsUpdated = libraryService().assignMemberToBook(id, isbn);
//			if (rowsUpdated > 0) {
//				logger.info("Book has been assigned successfully!");
//			} else {
//				logger.warning("Book was not assigned");
//			}
//
//		} catch (Exception e) {
//			logger.severe("An error has occurred: " + e.getMessage());
//			throw new WeberLibraryException(e.getMessage());
//		}
//	}
//
//	public static void updateBook() throws WeberLibraryException {
//
//		try {
//			BookDTO book = new BookDTO();
//			book.setAuthor("JK. Rowling");
//			book.setId(1);
//			int rowsUpdated = libraryService().updateBookAuthor(book);
//			if (rowsUpdated > 0) {
//				logger.info("Updated book Successfully!");
//			} else {
//				logger.warning("Book was not updated");
//			}
//		} catch (Exception e) {
//			logger.severe("An error has occurred: " + e.getMessage());
//			throw new WeberLibraryException(e.getMessage());
//		}
//	}
//
//	// DELETE
//	public static void deleteBook() throws WeberLibraryException {
//		try {
//			int id = 2;
//			int result = libraryService().deleteBook(id);
//			if (result > 0) {
//				logger.info("Deleted Successfully!");
//			} else {
//				logger.warning("Could not Delete Book.");
//			}
//		} catch (Exception e) {
//			logger.severe("An error has occurred: " + e.getMessage());
//			throw new WeberLibraryException(e.getMessage());
//		}
//	}

}
