package com.example.demo.service;

import com.example.demo.entity.Para9;
import com.example.demo.repository.para9Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class para9Service {
    @Autowired
    private para9Repository para9Repository;

    // 查询所有，按 id 升序
    public List<Para9> findAll() {
        return para9Repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

}
