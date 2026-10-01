package com.nt.hospital.controller;

import com.nt.hospital.model.Doctor;
import com.nt.hospital.model.User;
import com.nt.hospital.service.AddDepartmentService;
import com.nt.hospital.service.AdminService;
import com.nt.hospital.service.DoctorService;
import com.nt.hospital.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddDepartmentService addDepartmentService;


    @PostMapping("/admin/register-doctor")
    public String addDoctor(@ModelAttribute User user,
                            HttpSession doctorSession,
                            Model model) {

        user.setRole("DOCTOR");


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
    public String completeDroctor(@Validated @ModelAttribute Doctor doctor,
                                  BindingResult result,
                                  HttpSession doctorSession,
                                  Model model) {

        User user = (User) doctorSession.getAttribute("addDoctor");

        if (user == null) {

            model.addAttribute("error",
                    "Doctor session expired. Please register the doctor again.");

            return "Admin/addDoctor";
        }


        doctor.setUser(user);

        boolean isCompleted = doctorService.completeDroctor(doctor);

        if (isCompleted) {

            doctorSession.removeAttribute("addDoctor");

            model.addAttribute("success",
                    "Doctor added successfully");

            return "Admin/completeDoctor";

        } else {

            model.addAttribute("error",
                    "Doctor profile not added");

            return "Admin/completeDoctor";
        }
    }


    @GetMapping("/doctorsDashboard")
    public String doctorsDashboard(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {

            return "redirect:/UserLoginPage";
        }
        List<Doctor> doctorList = doctorService.getAllDoctorsData();

        long totalDoctors = doctorList.size();

        long activeDoctors = doctorList.stream().filter(d -> "ACTIVE".equalsIgnoreCase(d.getUser().getStatus())).count();
        long inactiveDoctors = doctorList.stream().filter(d -> "INACTIVE".equalsIgnoreCase(d.getUser().getStatus())).count();
        long specializationCount = doctorList.stream().map(Doctor::getSpecialization).filter(java.util.Objects::nonNull).distinct().count();
        model.addAttribute("totalDoctors", totalDoctors);
        model.addAttribute("activeDoctors", activeDoctors);
        model.addAttribute("inactiveDoctors", inactiveDoctors);
        model.addAttribute("specializationCount",specializationCount);


        return "Admin/doctorsDashboard";
    }

    @GetMapping("/manageAllDoctorsData")
    public String manageAllDoctorsData(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/UserLoginPage";
        }

        List<Doctor> doctorsData = doctorService.getAllDoctorsData();

        model.addAttribute("doctorsData", doctorsData);

        return "Admin/manageAllDoctors";
    }

    @GetMapping("/getAllDoctorsData")
    public String showAllDoctorPage(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/UserLoginPage";
        }

        List<Doctor> doctorsData = doctorService.getAllDoctorsData();
        long totalDoctors = doctorsData.size();

        long activeDoctors = doctorsData.stream().filter(d -> "ACTIVE".equalsIgnoreCase(d.getUser().getStatus())).count();
        long inactiveDoctors = doctorsData.stream().filter(d -> "INACTIVE".equalsIgnoreCase(d.getUser().getStatus())).count();
        long specializationCount = doctorsData.stream().map(Doctor::getSpecialization).filter(java.util.Objects::nonNull).distinct().count();
        model.addAttribute("totalDoctors", totalDoctors);
        model.addAttribute("activeDoctors", activeDoctors);
        model.addAttribute("inactiveDoctors", inactiveDoctors);
        model.addAttribute("specializationCount",specializationCount);


        model.addAttribute("doctorsData", doctorsData);

        return "Admin/showAllDoctorsDetails";
    }

    @GetMapping("/Admin/adminDashboard")
    public String getAllRecordCount(Model model) {

        long departmentCount = addDepartmentService.DepartmentCount();
        model.addAttribute("departmentCount", departmentCount);


        long doctorCount = doctorService.getAllDoctorCount();
        model.addAttribute("doctorCount", doctorCount);

        return "Admin/adminDashboard";


    }
}