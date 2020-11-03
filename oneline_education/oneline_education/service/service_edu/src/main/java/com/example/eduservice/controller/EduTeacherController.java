package com.example.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.MyException;
import com.example.commonutils.R;
import com.example.eduservice.entity.EduTeacher;
import com.example.eduservice.entity.vo.TeacherQuery;
import com.example.eduservice.service.EduTeacherService;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    private final EduTeacherService eduTeacherService;

    public EduTeacherController(EduTeacherService eduTeacherService) {
        this.eduTeacherService = eduTeacherService;
    }
    //1.查询讲师表所有数据
    @ApiOperation(value = "查询所有")
    @GetMapping("findAll")
    public R findAll(){
        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return R.ok().data("items",teacherList);
    }
    //2.删除某讲师
    @ApiOperation(value = "删除某个讲师")
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag)
            return R.ok();
        return R.error();
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询")
    @GetMapping("{current}/{size}")
    public R pageQuery(
            @PathVariable(required = true) Long current,
            @PathVariable(required = true) Long size){
        Page<EduTeacher> eduTeacherPage = new Page<>(current,size);
        eduTeacherService.page(eduTeacherPage,null);
        HashMap<String, Object> map = new HashMap<>(){{
            put("total",eduTeacherPage.getTotal());
            put("rows",eduTeacherPage.getRecords());
        }};
        return R.ok().data(map);
    }

    /**
     * 分页条件查询
     */
    @ApiOperation(value = "分页条件查询")
    @PostMapping("{current}/{size}")
    public R pageQueryCondition(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody TeacherQuery teacherQuery){
        Page<EduTeacher> page = new Page<>(current, size);
        eduTeacherService.pageQuery(page,teacherQuery);
        HashMap<String, Object> map = new HashMap<>(){{
            put("total",page.getTotal());
            put("rows",page.getRecords());
        }};
        return R.ok().data(map);
    }

    /**
     * 新增讲师
     */
    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(@RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.save(teacher);
        return R.okOrError(flag);
    }
    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping("{id}")
    public R getById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item",teacher);
    }
    /**
     * 根据id修改
     */
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(@PathVariable String id,@RequestBody EduTeacher eduTeacher){
        eduTeacher.setId(id);
        boolean b = eduTeacherService.updateById(eduTeacher);
        return R.okOrError(b);
    }
}

