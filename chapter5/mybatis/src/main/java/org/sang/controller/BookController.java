package org.sang.controller;

import lombok.extern.slf4j.Slf4j;
import org.sang.model.Book;
import org.sang.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/bookOps")
    public void bookOps(){
        Book b1 = new Book();
        b1.setName("西厢记");
        b1.setAuthor("王实普");
        int i = bookService.addBook(b1);
        log.warn("addBook >>> "+i);
        Book b2 = new Book();
        b2.setId(1);
        b2.setName("朝花夕拾");
        b2.setAuthor("鲁迅");
        int updateBook = bookService.updateBook(b2);
        log.warn("updateBook >>>"+updateBook);
        Book b3 = bookService.getBookById(i);
        log.warn("getBookById >>>"+b3);
        int delete = bookService.deleteBookById(2);
        log.warn("deleteBookById >>>"+delete);
        List<Book> bookList = bookService.getAllBooks();
        log.warn("getAllBooks>>>"+bookList);
    }

}
