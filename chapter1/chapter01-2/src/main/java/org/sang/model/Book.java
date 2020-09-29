package org.sang.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "book")
public class Book {
    private String name;
    private String author;
    private Float price;
}
