package org.sang.swagger2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Mr.Du
 * @date 2020/10/20 19:32
 */
@Data
@ApiModel(value = "用户实体类",description = "用户信息描述类")
public class User {
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户地址")
    private String address;
}
