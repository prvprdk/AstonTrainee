package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "local_s")
public class LocalStudent extends Student {
    private String area;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
