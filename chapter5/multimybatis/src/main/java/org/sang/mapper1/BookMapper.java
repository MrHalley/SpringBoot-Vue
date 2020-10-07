package org.sang.mapper1;

import org.sang.model.Book;

import java.util.List;

//@Mapper
public interface BookMapper {
    List<Book> findAll();
}
