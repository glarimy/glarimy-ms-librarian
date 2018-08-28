package com.glarimy.ms.librarian.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.glarimy.ms.librarian.model.Book;

@Repository
@Transactional
public interface LibraryRepo extends MongoRepository<Book, Integer> {
	public Book findByIsbn(int isbn);
}
