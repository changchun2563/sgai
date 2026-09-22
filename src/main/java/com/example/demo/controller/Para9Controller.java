package com.example.demo.controller;
import com.example.demo.entity.Para9;
import com.example.demo.service.para9Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")   // 添加基础路径
public class Para9Controller {
    @Autowired
    private para9Service para9Service;

    // 查询所有（按 id 升序）
     @GetMapping("/para9")
     public List<Para9> getPara9() {       return para9Service.findAll();           }

}
