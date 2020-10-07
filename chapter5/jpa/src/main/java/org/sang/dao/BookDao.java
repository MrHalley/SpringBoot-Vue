package org.sang.dao;

import org.sang.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Integer> {
    List<Book> getBooksByAuthorStartingWith(String author);
    List<Book> getBooksByPriceGreaterThan(Float price);
    //nativeQuery=true表示使用原生的SQL查询
    @Query(value = "select * from t_book where id=(select max(id) from t_book)",nativeQuery = true)
    Book getMaxIdBook();
    //使用jpql表达式
    @Query("select b from t_book b where b.id>:id and b.author=:author")
    List<Book> getBookByIdAndAuthor(@Param("author") String author, @Param("id") Integer id);
    @Query("select b from t_book b where b.id<?2 and b.name like %?1%")
    List<Book> getBooksByIdAndName(String name, Integer id);
}
