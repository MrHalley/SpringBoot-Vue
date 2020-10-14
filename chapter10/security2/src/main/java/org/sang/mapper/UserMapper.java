package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.model.Role;
import org.sang.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据用户名查找账户
     * @param username
     * @return
     */
    User loadUserByUsername(String username);

    /**
     * 根据用户id查找角色
     * @param uid
     * @return
     */
    List<Role> getUserRolesByUid(Integer uid);
}
