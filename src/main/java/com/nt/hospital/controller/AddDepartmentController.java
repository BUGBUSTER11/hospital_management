package com.nt.hospital.controller;

import com.nt.hospital.model.Department;
import com.nt.hospital.model.User;
import com.nt.hospital.service.AddDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AddDepartmentController {

    @Autowired
    private AddDepartmentService departmentService;


    @GetMapping("/Department/addDepartment")
    public String showAddDepartmentForm(Model model) {

        List<User> directors = departmentService.getDirectors();

        model.addAttribute("directors", directors);

        model.addAttribute("department", new Department());

        return "/Department/addDepartment";
    }


    @PostMapping("/Department/addDepartment")
    public String addDepartment(
            @ModelAttribute("department") Department department,
            @RequestParam("directorId") int directorId) {

        departmentService.addDepartment(department, directorId);

        return "redirect:/Admin/adminDashboard";
    }
}