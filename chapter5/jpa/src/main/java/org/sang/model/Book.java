package org.sang.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "book_name",nullable = false)
    private String name;
    private String author;
    private Float price;
    //生成数据库中的表时，该属性被忽略，即不生成对应的字段
    @Transient
    private String description;

}
