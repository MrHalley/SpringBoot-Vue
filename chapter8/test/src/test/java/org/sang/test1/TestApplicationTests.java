package org.sang.test1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sang.test1.model.Book;
import org.sang.test1.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {
    @Autowired
    private HelloService helloService;
    @Autowired
    WebApplicationContext wac;//声明一个 MockMvc对象,并在每个测试方法执行前进行MockMvc的初始化操作
    MockMvc mockMvc;
    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void contextLoad(){
        String hello = helloService.sayHello("Michael");
        Assert.hasText(hello,"Hello Michael !");
    }

    @Test
    public void test1() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/hello")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name","Michael"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void test2() throws Exception {
        ObjectMapper om = new ObjectMapper();
        Book b1 = new Book();
        b1.setId(1);
        b1.setName("罗贯中");
        b1.setAuthor("三国演义");
        String s = om.writeValueAsString(b1);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/book")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(s))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}

