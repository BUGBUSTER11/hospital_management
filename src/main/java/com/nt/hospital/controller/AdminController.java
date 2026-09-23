package com.nt.hospital.controller;

import com.nt.hospital.model.Doctor;
import com.nt.hospital.model.User;
import com.nt.hospital.service.AdminService;
import com.nt.hospital.service.DoctorService;
import com.nt.hospital.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;



    @PostMapping("/admin/register-doctor")
    public String addDoctor(@ModelAttribute User user,
                            HttpSession doctorSession,
                            Model model) {

        boolean isRegister = doctorService.addDoctor(user);

        if (isRegister) {

            User doctor = userService.getUserIdAndRoleByEmail(user.getEmail());

            doctorSession.setAttribute("addDoctor", doctor);

            model.addAttribute("success",
                    "Doctor account created successfully. Complete doctor profile.");

            return "Admin/completeDoctor";

        } else {

            model.addAttribute("error", "Doctor not added");

            return "Admin/addDoctor";
        }
    }


    @PostMapping("/admin/add-doctor-details")
    public String completeDroctor(@ModelAttribute Doctor doctor,
                                  HttpSession doctorSession,
                                  Model model) {

        User user = (User) doctorSession.getAttribute("addDoctor");

        if (user == null) {

            model.addAttribute("error",
                    "Doctor session expired. Please register the doctor again.");

            return "Admin/addDoctor";
        }

        int userId = user.getId();
        String useName = user.getName();
        String email = user.getEmail();
        Long contactNo = user.getContact();

        doctor.setUserId(userId);
        doctor.setDrName(useName);
        doctor.setEmail(email);
        doctor.setPhone(contactNo);

        boolean isCompleted = doctorService.completeDroctor(doctor);

        if (isCompleted) {

            doctorSession.removeAttribute("addDoctor");

            model.addAttribute("success",
                    "Doctor added successfully");

            return "Admin/adminDashboard";

        } else {

            model.addAttribute("error",
                    "Doctor profile not added");

            return "Admin/completeDoctor";
        }
    }
}