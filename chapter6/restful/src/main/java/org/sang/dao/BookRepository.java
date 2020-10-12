package org.sang.dao;

import org.sang.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 *  自定义请求路径
 *  默认情况下，请求路径都是实体类名小写加s
 *  http://localhost:8080/books
 */
@CrossOrigin  //配置cors跨域请求
@RepositoryRestResource(path = "bs"/*,collectionResourceRel = "bs",itemResourceRel = "bs"*/)
public interface BookRepository extends JpaRepository<Book,Integer> {
    @RestResource(path = "author",rel = "author")
    List<Book> findByAuthorContains(@Param("author") String author);
    @RestResource(path = "name",rel = "name")
    Book findByNameEquals(@Param("name") String name);

    @Override
    @RestResource(exported = false) //屏蔽deleteById接口
    void deleteById(Integer integer);
}
