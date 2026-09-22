package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alarm")
public class Alarm {
    @Id
    @Column(name = "dt", length = Integer.MAX_VALUE)
    private String dt;

    @Column(name = "item", length = Integer.MAX_VALUE)
    private String item;

    @Column(name = "desc1", length = Integer.MAX_VALUE)
    private String desc1;

    @Column(name = "area", length = Integer.MAX_VALUE)
    private String area;

    @Column(name = "ackstate")
    private Boolean ackstate;

    @Column(name = "acktime", length = Integer.MAX_VALUE)
    private String acktime;

    @Column(name = "ackoperater", length = Integer.MAX_VALUE)
    private String ackoperater;

    @Column(name = "type", length = Integer.MAX_VALUE)
    private String type;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Boolean getAckstate() {
        return ackstate;
    }

    public void setAckstate(Boolean ackstate) {
        this.ackstate = ackstate;
    }

    public String getAcktime() {
        return acktime;
    }

    public void setAcktime(String acktime) {
        this.acktime = acktime;
    }

    public String getAckoperater() {
        return ackoperater;
    }

    public void setAckoperater(String ackoperater) {
        this.ackoperater = ackoperater;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}