package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.model.Menu;
import org.sang.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {
    /**
     * 取得所有的menu
     * @return
     */
    List<Menu> getAllMenus();

}
