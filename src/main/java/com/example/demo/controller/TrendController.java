package com.example.demo.controller;
import com.example.demo.dto.TrendResponse;
import com.example.demo.service.TrendService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 历史趋势数据查询控制器
 * 对应前端 Vue 页面中的 axios.get('/api/trend/data', { params })
 */
@RestController
@RequestMapping("/api/trend")
public class TrendController {

    private final TrendService trendService;

    // 推荐使用构造器注入，便于测试且避免循环依赖
    public TrendController(TrendService trendService) {
        this.trendService = trendService;
    }

    /**
     * 查询历史趋势数据
     *
     * @param start 开始时间（ISO 8601 格式，例如 2026-09-21T08:50:00）
     * @param end   结束时间（ISO 8601 格式，例如 2026-09-21T09:00:00）
     * @param tags  标签列表，逗号分隔（例如 tag0,tag1,tag2）
     * @return 符合 ECharts 要求的 { times: [], values: {} } 结构
     */
    @GetMapping("/data")
    public TrendResponse getTrendData(
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime start,

            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime end,

            @RequestParam("tags") String tags) {

        // 1. 将前端传来的逗号分隔字符串拆分为 List
        // 例如 "tag0,tag1,tag2" -> ["tag0", "tag1", "tag2"]
        List<String> tagList = Arrays.stream(tags.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        // 2. 调用 Service 层进行查询和数据组装
        return trendService.queryTrend(start, end, tagList);
    }
}