package org.sang.service;

import org.sang.dao.BookDao;
import org.sang.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void addBook(Book book) {
        bookDao.save(book);
    }
    //分页查询
    public Page<Book> getpage(Pageable pageable) {
        return bookDao.findAll(pageable);
    }
    public List<Book> getBooksByAuthorStartingWith(String author){
        return bookDao.getBooksByAuthorStartingWith(author);
    }
    public List<Book> getBooksByPriceGreaterThan(Float price){
        return bookDao.getBooksByPriceGreaterThan(price);
    }
    public Book getMaxIdBook(){
        return bookDao.getMaxIdBook();
    }
    public List<Book> getBookByIdAndAuthor(String author, Integer id){
        return bookDao.getBookByIdAndAuthor(author, id);
    }
    public List<Book> getBooksByIdAndName(String name, Integer id){
        return bookDao.getBooksByIdAndName(name, id);
    }
}