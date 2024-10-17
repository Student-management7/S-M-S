package com.easyWay.Student_Management_System.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
public class FactCls {
    private String cls_name;

    private List<String> cls_sub;

    @Override
    public String toString() {
        return "FactCls{" +
                "cls_name='" + cls_name + '\'' +
                ", cls_sub=" + cls_sub +
                '}';
    }

    public String getCls_name() {
        return cls_name;
    }

    public void setCls_name(String cls_name) {
        this.cls_name = cls_name;
    }

    public List<String> getCls_sub() {
        return cls_sub;
    }

    public void setCls_sub(List<String> cls_sub) {
        this.cls_sub = cls_sub;
    }
}
