package com.glarimy.ms.librarian.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.glarimy.ms.librarian.data.LibraryRepo;
import com.glarimy.ms.librarian.model.Book;

@RestController
public class LibraryController {
	@Autowired
	private LibraryRepo repo;

	@RequestMapping(value = "/book/{isbn}", method = RequestMethod.GET)
	public ResponseEntity<Book> find(@PathVariable("isbn") int isbn) {
		System.out.println("ISBN: " + isbn);
		Book book = repo.findByIsbn(isbn);
		System.out.println("Book: " + book);
		if (book == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity<Book> add(@RequestBody Book book, UriComponentsBuilder builder) {
		System.out.println("Book: " + book);
		book = repo.save(book);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/book/{id}").buildAndExpand(book.getIsbn()).toUri());
		return new ResponseEntity<Book>(book, headers, HttpStatus.CREATED);
	}
}