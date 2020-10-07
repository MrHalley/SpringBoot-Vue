package org.sang.service;

import org.sang.mapper.BookMapper;
import org.sang.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }
    public int updateBook(Book book) {
        return bookMapper.updateBookById(book);
    }
    public int deleteBookById(Integer id) {
        return bookMapper.deleteBookById(id);
    }
    public Book getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }
}
