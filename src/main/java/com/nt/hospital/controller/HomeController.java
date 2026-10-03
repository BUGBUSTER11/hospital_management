package com.nt.hospital.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String landingPage(){
        return "index";
    }
//UserLogin Page
@GetMapping("/UserLoginPage")
public String userLoginPage(){
    return "Auth/adminLogin";
}

    // Add Doctor Page

    @GetMapping("/admin/doctors/add")
    public String addDoctorPage() {
        return "Admin/addDoctor";
    }

    // Admin Logout
    @GetMapping("/Userlogout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/UserLoginPage";
    }


}
