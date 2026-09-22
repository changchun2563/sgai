package com.example.demo.service;

import com.example.demo.entity.Para1;
import com.example.demo.repository.para1Repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class para1Service {
    @Autowired
    private para1Repository para1Repository;

    // 查询所有，按 id 升序
    public List<Para1> findAll() {
        return para1Repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Para1 updateValue(Long id, BigDecimal newValue) {
        Para1 entity = para1Repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Para1 not found with id: " + id));
        entity.setValue(newValue);
        return para1Repository.save(entity);
    }

    public Para1 updateRemark(Long id, String newRemark) {
        Para1 entity = para1Repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Para1 not found with id: " + id));
        entity.setRemark(newRemark);
        return para1Repository.save(entity);
    }

   // public List<Para1> findTop10() {
     //   return para1Repository.findTop10();   // 写法一
  //  }
}
