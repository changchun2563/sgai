package com.example.demo.service;

import com.example.demo.entity.Alarm;
import com.example.demo.repository.alarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlarmService {

    @Autowired
    private alarmRepository alarmRepository;

    // 查询所有，按 id 升序
    public List<Alarm> findAll() {
        return alarmRepository.findAll();
    }





}