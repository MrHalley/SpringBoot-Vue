package org.sang.controller;

import org.sang.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserSevice userSevice;

    public UserController(UserSevice userSevice) {
        this.userSevice = userSevice;
    }
    @GetMapping("/getUserById")
    public String getUserById(Integer id){
       return userSevice.getUserById(id);
    }
    @GetMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable Integer id){
        userSevice.deleteUserById(id);
    }
}
