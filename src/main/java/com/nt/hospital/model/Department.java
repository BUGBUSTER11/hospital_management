package com.nt.hospital.model;


import jakarta.persistence.*;

@Entity
public class Department {

    @Id
    private String departmentId;

    private String departmentName;

    private String description;

    private String location;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private User director;

    private long phone;

    public Department() {
    }

    public Department(String departmentId, String departmentName, String description,
                      String location, User director, long phone) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.description = description;
        this.location = location;
        this.director = director;
        this.phone = phone;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
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

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public User getDirector() {
        return director;
    }

    public void setDirector(User director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Department [departmentId=" + departmentId
                + ", departmentName=" + departmentName
                + ", description=" + description
                + ", location=" + location
                + ", director_id=" + director
                + ", phone=" + phone + "]";
    }
}