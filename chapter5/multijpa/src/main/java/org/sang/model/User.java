package org.sang.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "book_name")
    private String name;
    private String gender;
    private Integer age;
    @Transient
    private String description;
}
