package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Override
    public BookDto createBook(BookDto bookDto) {
        // TODO realize it
        bookDto.setId(22L);
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        // TODO realize it
        return null;
    }

    @Override
    public BookDto getBookById(Long id) {
        // TODO realize it
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
        // TODO realize it
    }
}
