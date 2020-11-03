package com.example.eduservice.controller;

import com.example.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mr.Du
 * @date 2020/11/2 14:18
 */
@Api(tags = "用户接口")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {
    @ApiOperation("用户登录接口")
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @ApiOperation("获取用户基本信息接口")
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
