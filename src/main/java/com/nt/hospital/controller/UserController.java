package com.nt.hospital.controller;

import com.nt.hospital.model.User;
import com.nt.hospital.service.AdminService;
import com.nt.hospital.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/adminLoginPage")
    public String adminLoginPage() {

        return "admin/adminLogin";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {

        User user = userService.loginUser(email, password);

        if (user != null) {

            session.setAttribute("loggedInUser", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("role", user.getRole());

            return "redirect:/admin/dashboard";
        }

        model.addAttribute("error", "Invalid email or password");

        return "admin/adminLogin";
    }


    @GetMapping("/admin/dashboard")
    public String adminDashboard() {

        return "admin/adminDashboard";
    }


    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/adminLoginPage";
    }
}
