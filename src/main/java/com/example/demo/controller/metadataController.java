package com.example.demo.controller;

import com.example.demo.entity.Realdatum;
import com.example.demo.service.realdataService;  // 建议类名大写开头
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class metadataController {

    @Autowired
    private realdataService realdataService;  // 如果类名改为 Para1Service，这里也要改

    // 查询所有
    @GetMapping("/realdata")
    public List<Realdatum> getRealdata() {
        return realdataService.findAll();
    }




}