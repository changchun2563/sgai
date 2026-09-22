package com.example.demo.controller;

import com.example.demo.entity.Para1;
import com.example.demo.service.para1Service;  // 建议类名大写开头
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Para1Controller {

    @Autowired
    private para1Service para1Service;  // 如果类名改为 Para1Service，这里也要改

    // 查询所有
    @GetMapping("/para1")
    public List<Para1> getPara1() {
        //return para1Service.findTop10();
        return para1Service.findAll();
    }



    // 更新 value
    @PutMapping("/para1/{id}/value")
    public Para1 updateValue(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String valueStr = payload.get("value");
        BigDecimal newValue = null;
        if (valueStr != null && !valueStr.trim().isEmpty()) {
            newValue = new BigDecimal(valueStr); // "0" -> BigDecimal 0
        }
        return para1Service.updateValue(id, newValue);
    }

    // 更新 remark
    @PutMapping("/para1/{id}/remark")
    public Para1 updateRemark(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String newRemark = payload.get("remark");
        return para1Service.updateRemark(id, newRemark);
    }
}