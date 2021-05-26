package com.ilife.ssa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/getUser")
    public String getUser(){
        return "我是个user";
    }
    @GetMapping("/testUser")
    public String testUser(){
        return "测试用户";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(){
        return "删除用户";
    }
}
