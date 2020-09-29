package org.sang.config;

import org.sang.User;
import org.sang.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private User user;
    @Autowired
    private Users users;

    @GetMapping("/user")
    public User user(){
        return user;
    }

    @GetMapping("/users")
    public Users users(){
        return users;
    }
}
