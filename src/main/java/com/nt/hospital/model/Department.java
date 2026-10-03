package com.nt.hospital.model;


import jakarta.persistence.*;

@Entity
public class Department {

    @Id
    private String id;

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

    public Department(String id, String departmentName, String description,
                      String location, String status , User director, long phone) {
        this.id = id;
        this.departmentName = departmentName;
        this.description = description;
        this.location = location;
        this.status = status;
        this.director = director;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                + ", departmentName=" + departmentName
                + ", description=" + description
                + ", location=" + location
                + ", director_id=" + director
                + ", phone=" + phone + "]";
    }
}