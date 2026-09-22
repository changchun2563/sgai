package com.example.demo.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data                   // 自动生成 Getter, Setter, toString, equals, hashCode
@NoArgsConstructor      // 自动生成无参构造器
@AllArgsConstructor     // 自动生成全参构造器

/**
 * 历史趋势查询响应 DTO
 * 用于匹配前端 ECharts 所需的数据格式
 */
public class TrendResponse {
    // 属性定义将在下一步完成
    private List<String> times;
    private Map<String, List<Double>> values;
}

