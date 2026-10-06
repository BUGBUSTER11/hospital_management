package com.nt.hospital.model;


import jakarta.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String departmentCode;

    private String departmentName;

    private String description;

    private String location;

    private String status;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private User director;

    private long phone;

    public Department() {
    }

    public Department(String departmentCode, String departmentName, String description,
                      String location, String status , User director, long phone) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.description = description;
        this.location = location;
        this.status = status;
        this.director = director;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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
        return "Department [departmentId=" + id
                + ", departmentCode=" + departmentCode
                + ", departmentName=" + departmentName
                + ", description=" + description
                + ", location=" + location
                + ", director_id=" + director
                + ", phone=" + phone + "]";
    }
}