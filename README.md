# Core Java Library API (No Framework)

A backend REST API built entirely from scratch using **Core Java**—no Spring Boot, no Hibernate, no external web framework. 

## Purpose
The goal of this project was to demonstrate a foundational understanding of what modern frameworks (like Spring Boot) are doing under the hood. By building the HTTP routing and database mapping manually, this project proves core competency in backend engineering.

## Features
- **Native HTTP Handling:** Utilizes `com.sun.net.httpserver.HttpServer` to manually route `GET`, `POST`, `PUT`, and `DELETE` requests.
- **Raw JDBC & SQL:** Implements custom database connections, `PreparedStatement` execution, and manual `ResultSet` mapping. No ORM used.
- **Relational Mapping:** Demonstrates a manual Many-To-One relationship between Books and Members via raw SQL JOINs.
- **JSON Serialization:** Uses Jackson `ObjectMapper` to manually parse request bodies and stringify responses.
- **Error Handling:** Custom exception hierarchy and resource bundles for externalized error messages.
- **Unit Testing:** Comprehensive test suite covering the controller and repository layers.

## Tech Stack
- **Language:** Java 17
- **HTTP:** `com.sun.net.httpserver` (Core Java)
- **Database:** MySQL
- **JSON:** Jackson
- **Build Tool:** Maven
- **Testing:** JUnit

## Endpoints
- `GET /api/library/books` — List all books
- `GET /api/library/memberByIsbn` — Find member by book ISBN
- `POST /api/library/insertBook` — Add a new book
- `POST /api/library/insertMember` — Add a new member
- `PUT /api/library/updateBookAuthor` — Update a book's author
- `PUT /api/library/assignMemberToBook` — Link a member to a book
- `DELETE /api/library/deleteBook` — Remove a book
