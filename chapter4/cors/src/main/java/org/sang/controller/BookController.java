package org.sang.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    /**
     * @CrossOrigin中的value表示支持的域，这里表示来自http://localhost:8081域的请求是支持域请求的
     * maxAge 表示探测请求的有效期，在前面的讲解中，读者已经了解到对于 DELETE、 PUT请求或者有自定义头
     * 信息的请求，在执行过程中会先发送探测请求，探测请求不用每次都发送，可以配直一个有效期，有效期过了之
     * 后才会发送探测请求。 这个属性，默认是 1800 秒，即 30 分钟。
     * allowedHeader表示允许的请求头，*表示所有的请求头都被允许
     * @param name
     * @return
     */
    @PostMapping("/")
    @CrossOrigin(value = "http://localhost:8081",maxAge = 1800,allowedHeaders = "*")
    public String addBook(String name){
        return "receive:" + name;
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(value = "http://localhost:8081",maxAge = 1800,allowedHeaders = "*")
    public String deleteBookId(@PathVariable Long id){
        return String.valueOf(id);
    }
}
