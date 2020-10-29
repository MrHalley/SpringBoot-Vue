package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println("---selectAll method test----");
        List<User> users = userMapper.selectList(null);
        //Assert.assertEquals(5,users.size());
        users.forEach(System.out::println);
    }
    @Test
    void addUser(){
        User user = new User();
        user.setAge(23);
        user.setName("东方不败");
        user.setEmail("hello@eamil.com");
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());

        userMapper.insert(user);
    }

    @Test
    void updateUser(){
        User user = new User();
        user.setId(6L);
        user.setAge(30);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    /**
     * 测试乐观锁
     * 当前对象的version（也是取出数据时数据库的version）和数据库中的version进行比较
     * 相等==》没人动过这个数据可以修改，修改后version+1
     * 不相等==》有人动过这个数据，无法修改
     */
    @Test
    void testOptimisticLocker(){
        User user = new User();
        user.setId(1320962197391761410L);
        user.setAge(13);
        user.setVersion(3);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    /**
     * 测试查询
     */
    @Test
    void testSelectDemo1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }
    @Test
    void testPage(){
        Page<User> userPage = new Page<>(1,3);
        userMapper.selectPage(userPage, null);
        System.out.println(userPage.getCurrent());
        System.out.println(userPage.getRecords());
        System.out.println(userPage.getSize());
        System.out.println(userPage.getTotal());
        System.out.println(userPage.getPages());
        System.out.println(userPage.hasNext());
        System.out.println(userPage.hasPrevious());
    }

    @Test
    public void testDeleteById(){
        int i = userMapper.deleteById(1L);
        System.out.println(i);
    }

    @Test
    public void testSelectList(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByAsc("age");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }
}
