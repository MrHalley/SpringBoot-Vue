package org.sang.validation.model;

import lombok.Data;
import org.sang.validation.group.ValidationGroup1;
import org.sang.validation.group.ValidationGroup2;

import javax.validation.constraints.*;

/**
 * @author Mr.Du
 * @date 2020/10/21 11:10
 */
@Data
public class User {
    private Integer id;
    //bug:f3f大小小于5，却没提示错误信息
    @Size(min = 5,max = 10,message = "{user.name.size}",groups = ValidationGroup1.class)
    private String name;
    @NotNull(message = "{user.address.notnull}",groups = ValidationGroup2.class)
    private String address;
    @DecimalMin(value = "1",message = "{user.age.size}")
    @DecimalMax(value = "200",message = "{user.age.size}")
    private Integer age;
    @Email(message = "{user.email.pattern}")
    @NotNull(message = "{user.email.notnull}",groups = {ValidationGroup1.class,ValidationGroup2.class})
    private String email;
}
