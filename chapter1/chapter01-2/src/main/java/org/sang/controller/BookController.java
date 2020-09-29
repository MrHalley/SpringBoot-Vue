package org.sang.controller;

import org.sang.model.Book;
import org.sang.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(Books.class)
public class BookController {
    private final Book book;
    private final Books books;

    public BookController(Book book, Books books) {
        this.book = book;
        this.books = books;
    }

    @GetMapping("/book")
    public Book book(){
        return book;
    }

    @GetMapping("/books")
    public Books books(){
        return books;
    }
}
