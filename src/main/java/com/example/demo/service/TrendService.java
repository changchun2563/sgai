package com.example.demo.service;

import com.example.demo.dto.TrendResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 历史趋势查询服务
 * 负责从 histdata 宽表中查询数据，并转换为前端 ECharts 所需的
 * { times: [], values: { tagN: [] } } 结构
 */
@Service
public class TrendService {

    private final JdbcTemplate jdbcTemplate;

    // 返回给前端的时间格式，与前端 formatDate 保持一致
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // 标签名白名单正则：只允许 tag + 数字，防止 SQL 注入
    private static final String TAG_PATTERN = "^tag\\d+$";

    public TrendService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 查询指定时间范围内、指定标签的历史趋势数据
     *
     * @param start 开始时间（含时区）
     * @param end   结束时间（含时区）
     * @param tags  标签列表，如 ["tag0", "tag1", "tag2"]
     * @return TrendResponse，包含 times 和 values
     */
    public TrendResponse queryTrend(OffsetDateTime start, OffsetDateTime end, List<String> tags) {

        // 1. 参数校验
        if (tags == null || tags.isEmpty()) {
            return new TrendResponse(Collections.emptyList(), Collections.emptyMap());
        }
        if (start == null || end == null) {
            throw new IllegalArgumentException("开始时间和结束时间不能为空");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("开始时间不能晚于结束时间");
        }

        // 2. 校验标签名合法性，防止 SQL 注入（列名不能用占位符，只能拼接）
        for (String tag : tags) {
            if (tag == null || !tag.matches(TAG_PATTERN)) {
                throw new IllegalArgumentException("非法的标签名: " + tag);
            }
        }

        // 3. 动态拼接 SQL，只查询 dt 和前端需要的标签列，避免 SELECT * 带来无谓开销
        String columnStr = "dt, " + String.join(", ", tags);
        String sql = "SELECT " + columnStr + " FROM histdata WHERE dt BETWEEN ? AND ? ORDER BY dt ASC";

        // 4. 执行查询，JdbcTemplate 会把结果每行封装为 Map<列名, 值>
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, start, end);

        if (rows.isEmpty()) {
            return new TrendResponse(Collections.emptyList(), Collections.emptyMap());
        }

        // 5. 提取时间轴（times）
        List<String> times = new ArrayList<>(rows.size());
        for (Map<String, Object> row : rows) {
            OffsetDateTime dt = toOffsetDateTime(row.get("dt"));
            times.add(dt != null ? dt.format(TIME_FORMATTER) : "");
        }

        // 6. 宽表转长表：为每个标签构建与 times 等长的数值数组
        Map<String, List<Double>> values = new LinkedHashMap<>();
        for (String tag : tags) {
            List<Double> series = new ArrayList<>(rows.size());
            for (Map<String, Object> row : rows) {
                Object valObj = row.get(tag);
                if (valObj == null) {
                    // 数据库为空，前端 ECharts 会断线
                    series.add(null);
                } else {
                    // numeric 列在 Java 中通常映射为 BigDecimal，统一转 Double
                    series.add(((Number) valObj).doubleValue());
                }
            }
            values.put(tag, series);
        }

        return new TrendResponse(times, values);
    }

    /**
     * 将 JDBC 返回的时间对象统一转换为 OffsetDateTime
     * 兼容 PostgreSQL 驱动可能返回的 OffsetDateTime 或 Timestamp
     */
    private OffsetDateTime toOffsetDateTime(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof OffsetDateTime) {
            return (OffsetDateTime) obj;
        }
        if (obj instanceof Timestamp) {
            return ((Timestamp) obj).toInstant().atOffset(ZoneOffset.UTC);
        }
        // 兜底：尝试解析字符串
        return OffsetDateTime.parse(obj.toString());
    }
}