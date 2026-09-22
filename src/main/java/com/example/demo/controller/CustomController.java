package com.example.demo.controller;

import com.example.demo.entity.Custom;
import com.example.demo.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")   // 添加基础路径
public class CustomController {

    @Autowired
    private CustomService customService;

    // 查询所有（按 id 升序）
    @GetMapping("/custom")
    public List<Custom> getCustoms() {
        return customService.findAll();
    }

    // 更新电话
    @PutMapping("/custom/{id}/phone")
    public Custom updatePhone(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String phone = payload.get("phone");
        return customService.updatePhone(id, phone);
    }
    //更新地址
    @PutMapping("/custom/{id}/address")
    public Custom updateAddress(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String address = payload.get("address");
        return customService.updateAddress(id, address);
    }
}