package org.sang.controller;

import lombok.extern.slf4j.Slf4j;
import org.sang.model.Book;
import org.sang.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/findAll")
    public void findAll(){
        //查询的页数，每页多少条数据
        PageRequest pageRequest = PageRequest.of(1, 3);
        Page<Book> page = bookService.getpage(pageRequest);
        log.warn("总页数:"+page.getTotalPages());
        log.warn("总记录数:"+page.getTotalElements());
        log.warn("查询结果:"+page.getContent());
        log.warn("当前页数:"+(page.getNumber()+1));
        log.warn("当前页面记录数:"+page.getNumberOfElements());
    }

    @GetMapping("/search")
    public void search(){
        List<Book> bs1 = bookService.getBookByIdAndAuthor("鲁迅", 7);
        List<Book> bs2 = bookService.getBooksByAuthorStartingWith("吴");
        List<Book> bs3 = bookService.getBooksByIdAndName("西", 8);
        List<Book> bs4 = bookService.getBooksByPriceGreaterThan(30F);
        Book b = bookService.getMaxIdBook();
        log.warn("bs1:"+bs1);
        log.warn("bs2:"+bs2);
        log.warn("bs3:"+bs3);
        log.warn("bs4:"+bs4);
        log.warn("b:"+b);
    }
    @GetMapping("/save")
    public void save(){
        Book book = new Book();
        book.setAuthor("鲁迅");
        book.setName("呐喊");
        book.setPrice(23F);
        bookService.addBook(book);
    }
}
