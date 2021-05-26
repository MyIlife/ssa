package com.ilife.ssa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @GetMapping("/error/403")
    public String error403(){
        return "没有访问权限";
    }
}
