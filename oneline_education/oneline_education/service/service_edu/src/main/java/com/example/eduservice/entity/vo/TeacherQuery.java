package com.example.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author Mr.Du
 * @date 2020/10/30 9:35
 */
@Data
@ApiModel(value = "Teacher查询对象",description = "讲师查询对象封装")
public class TeacherQuery {
    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师",example = "1")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间",example = "2019-01-01 10:10:10")
    //注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    private String begin;
    @ApiModelProperty(value = "查询结束时间",example = "2019-12-01 10:10:10")
    private String end;
}
