package com.nt.hospital.controller;

import com.nt.hospital.model.Doctor;
import com.nt.hospital.model.User;
import com.nt.hospital.service.AdminService;
import com.nt.hospital.service.DoctorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorService doctorService;


    @PostMapping("/admin/register-doctor")
    public String addDoctor(@ModelAttribute User user, HttpSession doctorSession, Model model) {

        boolean isRegister = doctorService.addDoctor(user);

        if (isRegister) {

            doctorSession.setAttribute("addDoctor", user);
            doctorSession.setAttribute("userId", user.getId());
            doctorSession.setAttribute("userName", user.getName());

            model.addAttribute("success","Doctor add successfully");

        }else {
            model.addAttribute("error","Doctor not added");

        }


        return "Admin/addDoctor";

    }

    @GetMapping("/admin/completedroctor")
    public String completeDoctorPage(){
        return "Admin/completeDoctor";
    }

    @PostMapping("/admin/add-doctor-details")
    public  String completeDroctor(@ModelAttribute Doctor doctor, HttpSession doctorSession){

        User user = (User) doctorSession.getAttribute("addDoctor");

        int userId = user.getId();
        doctor.setUserId(userId);



       boolean isCompleted =  doctorService.completeDroctor(doctor);



        return "Admin/adminDashboard";

    }
}