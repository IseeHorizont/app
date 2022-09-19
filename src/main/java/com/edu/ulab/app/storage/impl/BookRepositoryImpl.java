package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.exception.BookRepositoryException;
import com.edu.ulab.app.storage.Repository;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRepositoryImpl implements Repository<Book> {

    private final Storage storage;

    public BookRepositoryImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public List<Book> getAll() {
        return storage.findAllBook();
    }

    @Override
    public Book getById(Long id) {
        if (id == null) {
            return null;
        }
        return storage.findBookById(id);
    }

    @Override
    public Book create(Book book) {
        if (book == null) {
            return null;
        }
        Book createdBook = storage.createBook(book);
        return createdBook;
    }

    @Override
    public Book update(Book book) {
        if (book == null) {
            return null;
        }
        Book updatedBook = storage.updateBook(book);
        return updatedBook;
    }

    @Override
    public Book deleteById(Long id) {
        if (id == null) {
           throw new BookRepositoryException("Book with id #" + id + " is not exist");
        }
        Book deletedBook = storage.deleteBookById(id);
        return deletedBook;
    }
}
