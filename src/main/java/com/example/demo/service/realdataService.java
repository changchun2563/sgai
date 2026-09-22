package com.example.demo.service;

import com.example.demo.entity.Realdatum;
import com.example.demo.repository.realdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class realdataService {
    @Autowired
    private realdataRepository realdataRepository;

    // 查询所有，按 id 升序
    public List<Realdatum> findAll() {
        return realdataRepository.findAll();
    }

}
