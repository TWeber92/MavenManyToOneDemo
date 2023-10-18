package com.weber.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.weber.LibraryApp;
import com.weber.dto.BookDTO;
import com.weber.dto.MemberDTO;
import com.weber.exception.WeberLibraryException;
import com.weber.service.LibraryService;

public class ResponseHandler implements HttpHandler {

	private final LibraryService libraryService;
	private final Logger logger;
	private final ObjectMapper objMapper;
	private final ResourceBundle message;
	
	public ResponseHandler(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.objMapper = new ObjectMapper();
        this.logger = Logger.getLogger(LibraryApp.class.getName());
        this.message = ResourceBundle.getBundle("err-messages");
    }

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String requestMethod = exchange.getRequestMethod();
		String path = exchange.getRequestURI().getPath();

		try {
			if ("GET".equalsIgnoreCase(requestMethod) && "/api/library/books".equals(path)) {
				List<BookDTO> response = selectBooks();
				if (response.isEmpty()) {
					logWarningAndSendResponse(exchange, 404, message.getString("empty.books"));

				} else {
					sendResponse(exchange, response);
				}
			} else if ("GET".equalsIgnoreCase(requestMethod) && "/api/library/memberByIsbn".equals(path)) {
				JsonNode jsonNode = objMapper.readTree(exchange.getRequestBody());
				String isbn = jsonNode.get("isbn").asText();
				List<MemberDTO> response = selectMemberByISBN(isbn);
				if (response.isEmpty()) {
					logWarningAndSendResponse(exchange, 404, message.getString("empty.membersIsbn"));
				} else {
					sendResponse(exchange, response);
				}
			} else if ("POST".equalsIgnoreCase(requestMethod) && "/api/library/insertMember".equals(path)) {
				JsonNode node = objMapper.readTree(exchange.getRequestBody());
				String firstName = node.get("firstName").asText();
				String lastName = node.get("lastName").asText();
				int rowsInserted = insertMember(firstName, lastName);
				if (rowsInserted == 0) {
					logWarningAndSendResponse(exchange, 404, message.getString("err.memberInsert"));
				} else {
					sendResponse(exchange, rowsInserted);
				}
			} else if ("POST".equalsIgnoreCase(requestMethod) && "/api/library/insertBook".equals(path)) {
				JsonNode node = objMapper.readTree(exchange.getRequestBody());
				String title = node.get("title").asText();
				String author = node.get("author").asText();
				String isbn = node.get("isbn").asText();
				int rowsInserted = insertBook(title, author, isbn);
				if (rowsInserted == 0) {
					logWarningAndSendResponse(exchange, 404, message.getString("err.bookInsert"));
				} else {
					sendResponse(exchange, rowsInserted);
				}
			} else if ("PUT".equalsIgnoreCase(requestMethod) && "/api/library/updateBookAuthor".equals(path)) {
				JsonNode node = objMapper.readTree(exchange.getRequestBody());
				String author = node.get("author").asText();
				int bookId = node.get("bookId").asInt();
				int rowsUpdated = updateBookAuthor(author, bookId);
				if (rowsUpdated == 0) {
					logWarningAndSendResponse(exchange, 404, message.getString("err.authorUpdate"));
				} else {
					sendResponse(exchange, rowsUpdated);
				}
			} else if ("PUT".equalsIgnoreCase(requestMethod) && "/api/library/assignMemberToBook".equals(path)) {
				JsonNode node = objMapper.readTree(exchange.getRequestBody());
				int memberId = node.get("memberId").asInt();
				String isbn = node.get("isbn").asText();
				int rowsUpdated = assignMemberToBook(memberId, isbn);
				if (rowsUpdated == 0) {
					logWarningAndSendResponse(exchange, 404, message.getString("err.memberInsert"));
				} else {
					sendResponse(exchange, rowsUpdated);
				}
			} else if ("DELETE".equalsIgnoreCase(requestMethod) && "/api/library/deleteBook".equals(path)) {
				JsonNode node = objMapper.readTree(exchange.getRequestBody());
				int bookId = node.get("bookId").asInt();
				int delrows = deleteBook(bookId);
				if (delrows == 0) {
					logWarningAndSendResponse(exchange, 404, message.getString("err.bookDelete"));
				} else {
					sendResponse(exchange, delrows);
				}
			} else {
				sendErrorResponse(exchange, 404, "Page Not Found");
			}
		} catch (WeberLibraryException e) {
			logger.warning("An error has occurred: " + e.getMessage());
			sendErrorResponse(exchange, 500, e.getMessage());
		}

	}

	private List<MemberDTO> selectMemberByISBN(String isbn) throws WeberLibraryException {
		try {
			return libraryService.selectMemberByISBN(isbn);
		} catch (Exception e) {
			throw new WeberLibraryException(e.getMessage());
		}
	}

	public List<BookDTO> selectBooks() throws WeberLibraryException {
		try {
			return libraryService.selectBooks();
		} catch (Exception e) {
			throw new WeberLibraryException(e.getMessage());
		}
	}

	private int insertMember(String firstName, String lastName) throws WeberLibraryException {
		try {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setFirstName(firstName);
			memberDTO.setLastName(lastName);
			return libraryService.insertMember(memberDTO);
		} catch (Exception e) {
			throw new WeberLibraryException(e.getMessage());
		}
	}

	private int insertBook(String title, String author, String isbn) throws WeberLibraryException {
		try {
			BookDTO bookDTO = new BookDTO();
			bookDTO.setTitle(title);
			bookDTO.setAuthor(author);
			bookDTO.setIsbn(isbn);
			return libraryService.insertBook(bookDTO);
		} catch (Exception e) {
			throw new WeberLibraryException(e.getMessage());
		}
	}

	private int updateBookAuthor(String author, int bookId) throws WeberLibraryException {
		try {
			BookDTO bookDTO = new BookDTO();
			bookDTO.setId(bookId);
			bookDTO.setTitle("Blank");
			bookDTO.setAuthor(author);
			bookDTO.setIsbn("1234567890");
			return libraryService.updateBookAuthor(bookDTO);
		} catch (Exception e) {
			throw new WeberLibraryException(e.getMessage());
		}

	}

	private int assignMemberToBook(int memberId, String isbn) throws WeberLibraryException {
		try {
			return libraryService.assignMemberToBook(memberId, isbn);
		} catch (Exception e) {
			throw new WeberLibraryException(e.getMessage());
		}

	}

	private int deleteBook(int bookId) throws WeberLibraryException {
		try {
			return libraryService.deleteBook(bookId);
		} catch (Exception e) {
			throw new WeberLibraryException(e.getMessage());
		}
	}

	private void logWarningAndSendResponse(HttpExchange exchange, int status, String errMsg) throws IOException {
		logger.warning(errMsg);
		sendErrorResponse(exchange, status, errMsg);

	}

	public void sendErrorResponse(HttpExchange exchange, int statusCode, String errorMessage) throws IOException {
		exchange.sendResponseHeaders(statusCode, errorMessage.getBytes().length);
		OutputStream os = exchange.getResponseBody();
		os.write(errorMessage.getBytes());
		os.close();
	}

	public <T> void sendResponse(HttpExchange exchange, T response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonResponse = objectMapper.writeValueAsString(response);
		exchange.sendResponseHeaders(200, jsonResponse.length());
		exchange.getResponseHeaders().set("Content-Type", "application/json");
		OutputStream os = exchange.getResponseBody();
		os.write(jsonResponse.getBytes());
		os.close();
	}

}