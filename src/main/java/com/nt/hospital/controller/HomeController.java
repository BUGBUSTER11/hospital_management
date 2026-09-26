package com.nt.hospital.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Home page
    @GetMapping("/")
    public String landingPage() {
        return "index";
    }


    // Admin Login Page
    @GetMapping("/adminLoginPage")
    public String adminLoginPage() {
        return "Auth/adminLogin";
    }


    // Admin Dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Admin/adminDashboard";
    }


    // Add Department
    @GetMapping("/admin/departments")
    public String departmentDashboard() {
        return "Department/addDepartment";
    }


    // Add Doctor Page
    @GetMapping("/admin/doctors/add")
    public String addDoctorPage() {
        return "Admin/addDoctor";
    }


    // Admin Logout
    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/adminLoginPage";
    }

}