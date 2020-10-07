package org.sang.controller;

import lombok.extern.slf4j.Slf4j;
import org.sang.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class BookController {
    @Resource(name = "jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne;

    private final JdbcTemplate jdbcTemplateTwo;

    public BookController(@Qualifier("jdbcTemplateTwo") JdbcTemplate jdbcTemplateTwo) {
        this.jdbcTemplateTwo = jdbcTemplateTwo;
    }

    @GetMapping("/test1")
    public void test1(){
        List<Book> book1 = jdbcTemplateOne.query("select * from book",new BeanPropertyRowMapper<>(Book.class));
        List<Book> book2 = jdbcTemplateTwo.query("select * from book",new BeanPropertyRowMapper<>(Book.class));
        log.warn("book1:"+book1);
        log.warn("book2:"+book2);
    }
}
