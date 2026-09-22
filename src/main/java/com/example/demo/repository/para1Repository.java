package com.example.demo.repository;

import com.example.demo.entity.Para1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface para1Repository extends JpaRepository<Para1, Long> {

    // 按 id 升序取前 10 条
   // @Query(value = "SELECT * FROM para1 ORDER BY id ASC LIMIT 10", nativeQuery = true)
   // List<Para1> findTop10();
}


