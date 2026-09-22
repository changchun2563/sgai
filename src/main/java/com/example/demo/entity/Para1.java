package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "para1")
public class Para1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")              // 明确指定列名
    private Long id;

    @Column(name = "tagname", nullable = false, length = 50)
    private String tagname;

    @Column(name = "value", length = 100)
    private BigDecimal value;

    @Column(name = "remark", length = 50)
    private String remark;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTagname() {
        return tagname;
    }
    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public BigDecimal getValue() {
                return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
