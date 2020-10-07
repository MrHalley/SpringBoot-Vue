package org.sang.controller;

import lombok.extern.slf4j.Slf4j;
import org.sang.mapper1.BookMapper;
import org.sang.mapper2.BookMapper2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BookController {
    BookMapper bookMapper;
    BookMapper2 bookMapper2;
    public BookController(BookMapper bookMapper, BookMapper2 bookMapper2) {
        this.bookMapper = bookMapper;
        this.bookMapper2 = bookMapper2;
    }


    @GetMapping("/test")
    public void test(){
        log.warn(bookMapper.findAll().toString());
        log.warn(bookMapper2.findAll().toString());
    }

}
