package org.sang.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
//@Component
@ConfigurationProperties("books")
public class Books {
    List<Book> bookList;
}
