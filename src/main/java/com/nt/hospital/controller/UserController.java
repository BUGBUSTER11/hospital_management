package com.nt.hospital.controller;

import com.nt.hospital.model.User;
import com.nt.hospital.service.AdminService;
import com.nt.hospital.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/Auth/adminLogin")
    public String adminLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {

        User user = userService.loginUser(email, password);

        if (user != null) {

            session.setAttribute("loggedInUser", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("role", user.getRole());

            return "redirect:/Admin/adminDashboard";
        }

        model.addAttribute("error", "Invalid email or password");

        return "admin/adminLogin";
    }

    @PostMapping("/admin/add-director")
    public String addDirector(@ModelAttribute User user,Model model){

       boolean isAdded= userService.addDirector(user);

       if(isAdded){
           model.addAttribute("success","Director is Added Sucssesful...!");

       }else{

           model.addAttribute("error","Director is Not added email already exist...!");

       }
     return "Users/AddDirector";

    }




}
