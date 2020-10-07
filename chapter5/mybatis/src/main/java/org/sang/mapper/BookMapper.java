package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.model.Book;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Mapper
public interface BookMapper {
    int addBook(Book book);
    int deleteBookById(Integer id);
    int updateBookById(Book book);
    Book getBookById(Integer id);
    List<Book> getAllBooks();
}
