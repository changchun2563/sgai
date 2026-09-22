package com.example.demo.service;

import com.example.demo.entity.Custom;
import com.example.demo.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomService {

    @Autowired
    private CustomRepository customRepository;

    // 查询所有，按 id 升序
    public List<Custom> findAll() {
        return customRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    // 更新电话
    @Transactional
    public Custom updatePhone(Long id, String phone) {
        // 1. 根据ID查找数据，如果找不到抛出异常
        Custom custom = customRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("数据不存在，id: " + id));

        // 2. 更新电话字段
        custom.setPhone(phone);

        // 3. 保存回数据库
        return customRepository.save(custom);
    }

    // 更新地址
    @Transactional
    public Custom updateAddress(Long id, String address) {
        // 1. 根据ID查找数据，如果找不到抛出异常
        Custom custom = customRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("数据不存在，id: " + id));

        // 2. 更新电话字段
        custom.setAddress(address);

        // 3. 保存回数据库
        return customRepository.save(custom);
    }

}