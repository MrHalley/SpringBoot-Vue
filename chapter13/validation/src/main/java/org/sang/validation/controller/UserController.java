package org.sang.validation.controller;

import org.sang.validation.group.ValidationGroup1;
import org.sang.validation.group.ValidationGroup2;
import org.sang.validation.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Du
 * @date 2020/10/21 11:20
 */
@RestController
public class UserController {
    @PostMapping("/user")
    public List<String> addUser(@Validated(ValidationGroup1.class) User user, BindingResult result){
        List<String> errors = new ArrayList<>();
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            for(ObjectError error : allErrors){
                errors.add(error.getDefaultMessage());
            }
        }
        return errors;
    }
}
