package com.nt.hospital.model;


import jakarta.persistence.*;

@Entity
public class Department {

    @Id
    private int departmentId;

    private String departmentName;

    private String description;

    private String location;

    private String phone;

    public Department() {
    }

    public Department(String departmentName, String description,
                      String location, String phone) {
        this.departmentName = departmentName;
        this.description = description;
        this.location = location;
        this.phone = phone;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Department [departmentId=" + departmentId
                + ", departmentName=" + departmentName
                + ", description=" + description
                + ", location=" + location
                + ", phone=" + phone + "]";
    }
}