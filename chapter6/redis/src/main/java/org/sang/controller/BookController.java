package org.sang.controller;

import lombok.extern.slf4j.Slf4j;
import org.sang.model.Book;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BookController {
    private final RedisTemplate redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    public BookController(RedisTemplate redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/test1")
    public void test1(){
        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
        ops1.set("name","三国演义");
        String name = ops1.get("name");
        log.warn(name);
        ValueOperations ops2 = redisTemplate.opsForValue();
        Book b1 = new Book();
        b1.setId(1);
        b1.setName("红楼梦");
        b1.setAuthor("曹雪芹");
        ops2.set("b1",b1);//注意book类实现Serializable接口，否则报错IllegalArgumentException
        Book book = (Book) ops2.get("b1");
        log.warn(book.toString());
    }
}
