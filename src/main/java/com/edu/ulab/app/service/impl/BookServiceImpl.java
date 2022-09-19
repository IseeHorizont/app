package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.BookMapperImpl;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.impl.BookRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryImpl bookRepository;
    private final BookMapperImpl bookMapper;

    public BookServiceImpl(BookRepositoryImpl bookRepository, BookMapperImpl bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book createdBook = bookRepository.create(bookMapper.bookDtoToBook(bookDto));
        return bookMapper.bookToBookDto(createdBook);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book updatedBook = bookRepository.update(bookMapper.bookDtoToBook(bookDto));
        return bookMapper.bookToBookDto(updatedBook);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> allBooks = bookRepository.getAll();
        return allBooks.stream()
                .map(book -> bookMapper.bookToBookDto(book))
                .toList();
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
