package org.sang.service;

import org.sang.dao.BookDao;
import org.sang.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public int addBook(Book book){
        return bookDao.addBook(book);
    }

    public int updateBook(Book book){
        return bookDao.updateBook(book);
    }

    public int deleteBookById(Integer id){
        return bookDao.deleteBookById(id);
    }

    public Book getBookById(Integer id){
        return bookDao.getBookById(id);
    }

    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }
}

