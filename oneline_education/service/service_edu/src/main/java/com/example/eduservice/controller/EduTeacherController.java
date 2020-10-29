package com.example.eduservice.controller;


import com.example.commonutils.R;
import com.example.eduservice.entity.EduTeacher;
import com.example.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author verymodest
 * @since 2020-10-28
 */
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    private final EduTeacherService eduTeacherService;

    public EduTeacherController(EduTeacherService eduTeacherService) {
        this.eduTeacherService = eduTeacherService;
    }
    //1.查询讲师表所有数据
    @ApiOperation(value = "查询所有")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "所有讲师"),
            @ApiResponse(code = 500,message = "查询失败")
    })
    @GetMapping("findAll")
    public R findAll(){
        List<EduTeacher> teacherList = eduTeacherService.list(null);
        System.out.println("hfd");
        return R.ok().data("items",teacherList);
    }
    //2.删除某讲师
    @ApiOperation(value = "删除某个讲师")
    @DeleteMapping("{id}")
    public R deleteEduTeacher(@PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag)
            return R.ok();
        return R.error();
    }
}

