package com.example.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;

@Data
public class TrendQueryRequest {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime start;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime end;

    private String tags; // 接收 "tag0,tag1" 字符串

}
