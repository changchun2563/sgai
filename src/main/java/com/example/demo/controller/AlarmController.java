package com.example.demo.controller;
import com.example.demo.entity.Alarm;
import com.example.demo.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")   // 添加基础路径
public class AlarmController {
    @Autowired
    private AlarmService alarmService;

    // 查询所有（按 id 升序）
    @GetMapping("/alarm")
    public List<Alarm> getAlarms() {
        System.out.println("diaoyong ");
        return alarmService.findAll();
    }
}