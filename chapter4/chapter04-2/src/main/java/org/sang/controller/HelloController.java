package org.sang.controller;

import org.sang.model.Author;
import org.sang.model.Book;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static javax.swing.text.html.CSS.getAttribute;

@RestController
public class HelloController {

    @GetMapping("/info")
    public void info(Model model){
        Map<String, Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Object value = map.get(key);
            System.out.println(key + ">>>" +value);
        }
    }

    @GetMapping("/book")
    public String book(@ModelAttribute("a") Book book, @ModelAttribute("b") Author author){
        return book.toString() + ">>>" + author.toString();
    }

    @GetMapping("/hello")
    public String hello(){
        int i = 1 / 0;
        return "hello";
    }
}
