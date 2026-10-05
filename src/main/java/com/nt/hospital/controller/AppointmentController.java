package com.nt.hospital.controller;


import com.nt.hospital.model.Appointment;
import com.nt.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Open appointment form karay cha asel ter
    //appointment index.html file madhli ref aahe
    @GetMapping("/appointment")
    public String appointmentPage() {
        return "Appointment/appointment";
    }

  //appointment madhali  from chi action
  @PostMapping("/appointmentrequest")
  public String requestAppointment(@ModelAttribute Appointment appointment) {


    Appointment isBooked =  appointmentService.requestAppointment(appointment);
    if (isBooked !=null){
        return "redirect:/appointment";
    }
      return "/";
  }
}