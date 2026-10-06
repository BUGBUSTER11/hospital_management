package com.nt.hospital.controller;

import com.nt.hospital.model.Department;
import com.nt.hospital.model.Doctor;
import com.nt.hospital.model.User;
import com.nt.hospital.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private DepartmentService departmentService;

//register new User as a doctor
    @PostMapping("/admin/register-doctor")
    public String addDoctor(@ModelAttribute User user,
                            HttpSession doctorSession,
                            Model model) {

        user.setRole("DOCTOR");

        //list of departments
        List<Department> departments = departmentService.getAllDepartmets();
        model.addAttribute("departments",departments);



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

// Complete Doctor profill
    @PostMapping("/admin/add-doctor-details")
    public String completeDroctor(@Validated @ModelAttribute Doctor doctor,
                                  BindingResult result, @RequestParam int departmentId,
                                  HttpSession doctorSession,
                                  Model model) {

        User user = (User) doctorSession.getAttribute("addDoctor");



        if (user == null) {

            model.addAttribute("error",
                    "Doctor session expired. Please register the doctor again.");

            return "Admin/addDoctor";
        }

        Department department = addDepartmentService.getDepartmentById(departmentId);

        doctor.setUser(user);
        doctor.setDepartment(department);

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

//Doctor dashboard page and get all data show on this page
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
        model.addAttribute("specializationCount", specializationCount);


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
        model.addAttribute("specializationCount", specializationCount);


        model.addAttribute("doctorsData", doctorsData);

        return "Admin/showAllDoctorsDetails";
    }

    @GetMapping("/admin/department")
    public String departmentDashboard(Model model) {

        // Get all departments
        List<Department> departments =
                departmentService.getAllDepartmets();

        // Total department count
        long departmentCount =
                addDepartmentService.DepartmentCount();

        // Total doctors
        long doctorCount =
                doctorService.getAllDoctorCount();

        long activeDepCount = departments.stream().filter(department -> "ACTIVE".equalsIgnoreCase(department.getStatus())).count();
        long inactiveDepCount = departments.stream().filter(department -> "INACTIVE".equalsIgnoreCase(department.getStatus())).count();


        // Send data to dashboard
        model.addAttribute("departments", departments);
        model.addAttribute("departmentCount", departmentCount);
        model.addAttribute("doctorCount", doctorCount);
        model.addAttribute("activeDepCount", activeDepCount);
        model.addAttribute("inactiveDepCount", inactiveDepCount);

        // Use the SAME name that the HTML will use
        model.addAttribute("totalDepartments", departmentCount);

        return "Admin/departmentManagement";
    }

    @GetMapping("/Admin/adminDashboard")
    public String adminDashboard() {
        return "Admin/adminDashboard";
    }

    @GetMapping("/admin/departments")
    public String showAllDepartments(Model model) {

        // Get all departments from database
        List<Department> departments =
                departmentService.getAllDepartmets();

        // Calculate total
        long totalDepartments =
                departments.size();

        // Send department list to HTML
        model.addAttribute("departments", departments);

        // Send count to HTML
        model.addAttribute("totalDepartments", totalDepartments);

        return "Department/viewDepartmentList";
    }

    //Get All Active Department And Send to the Html Page To show list
    @GetMapping("/admin/activeDepView")
    public String activeDepartmentView(Model model) {

        List<Department> departments = addDepartmentService.getActiveDepartments();

        long totalActiveDepartments = departments.size();

        model.addAttribute("activeDepartments", departments);
        model.addAttribute("totalActiveDepartments", totalActiveDepartments);

        return "Department/viewActiveDepList";
    }

    @GetMapping("/admin/inactiveDepView")
    public String inactiveDepartmentView(Model model) {

        List<Department> departments = addDepartmentService.getInActiveDepartments();

        long totalInActiveDepartments = departments.size();

        model.addAttribute("InactiveDepartments", departments);
        model.addAttribute("totalActiveDepartments", totalInActiveDepartments);

        return "Department/viewInActiveDepList";
    }


    //Find Doctor for the update their profill
    @PostMapping("/admin/find-doctor")
    public String findDoctor(
            @RequestParam("email") String email,
            Model model) {

        Doctor doctor = doctorService.getDoctorByEmail(email);

        if (doctor != null) {
            model.addAttribute("doctor", doctor);

        } else {
            model.addAttribute(
                    "error",
                    "Doctor not found with email: " + email
            );
        }

        return "Admin/updateDoctor";
    }
//update Doctor Profill Using email
    @PostMapping("/admin/update-doctor")
    public String updateDoctor(
            @RequestParam("doctorId") int doctorId,
            @RequestParam("email") String email,
            @RequestParam("contact") long contact,
            @RequestParam("status") String status,
            @RequestParam("specialization") String specialization,
            @RequestParam("qualification") String qualification,
            @RequestParam("licenseNumber") String licenseNumber,
            @RequestParam("experience") int experience,
            @RequestParam("consultationFee") BigDecimal consultationFee,
            @RequestParam("joiningDate") LocalDate joiningDate,
            Model model) {


        Doctor doctor = doctorService.getDoctorById(doctorId);

        if (doctor == null) {
            model.addAttribute("error", "Doctor not found");
            return "Admin/updateDoctor";
        }

        User user = doctor.getUser();

        //set all form data in the object class for the update
        user.setEmail(email);
        user.setContact(contact);
        user.setStatus(status);
        doctor.setSpecialization(specialization);
        doctor.setQualification(qualification);
        doctor.setLicenseNumber(licenseNumber);
        doctor.setExperience(experience);
        doctor.setConsultationFee(consultationFee);
        doctor.setJoiningDate(joiningDate);

        //update all doctor details method and show result on page
        boolean isupdated = doctorService.updateDoctor(doctor);

        if (isupdated) {

            model.addAttribute("success",
                    "Doctor profile updated successfully.");

            model.addAttribute("doctor", doctor);

        } else {

            model.addAttribute("error",
                    "Doctor profile update failed.");

            model.addAttribute("doctor", doctor);
        }

        return "Admin/updateDoctor";
    }
//Show all Only Active doctor on Doctor dashboard
    @GetMapping("/admin/active-doctors")
    public String showActiveDoctors(HttpSession session, Model model){

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/UserLoginPage";
        }

        List<Doctor> doctorsData = doctorService.getAllDoctorsData();

        List<Doctor> activeDoctors = doctorsData.stream().filter(doctor -> doctor.getUser().getStatus() != null && doctor.getUser().getStatus().equalsIgnoreCase("ACTIVE")).collect(Collectors.toList());
        long specializationCount = doctorsData.stream().map(Doctor::getSpecialization).filter(java.util.Objects::nonNull).distinct().count();

        model.addAttribute("specializationCount",specializationCount);
        model.addAttribute("activeDoctors", activeDoctors);
        return "Admin/allActiveDoctors";
    }
    //Show all Only InActive doctor on Doctor dashboard

    @GetMapping("/admin/inactive-doctors")
    public String showInActiveDoctors(HttpSession session,Model model){

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/UserLoginPage";
        }

        List<Doctor> doctorsData = doctorService.getAllDoctorsData();

        List<Doctor> inactiveDoctors = doctorsData.stream().filter(doctor -> doctor.getUser().getStatus() != null && doctor.getUser().getStatus().equalsIgnoreCase("INACTIVE")).collect(Collectors.toList());
        long specializationCount = doctorsData.stream().map(Doctor::getSpecialization).filter(java.util.Objects::nonNull).distinct().count();

        model.addAttribute("specializationCount",specializationCount);
        model.addAttribute("inactiveDoctors", inactiveDoctors);

        return "Admin/InActiveDoctors";
    }

    @GetMapping("/admin/showDepartmentDirector")
    public String showAllDepartmentsDirector(Model model) {

        // Get all departments from database
        List<Department> departments =
                departmentService.getAllDepartmets();

        // Calculate total
        long totalDepartments =
                departments.size();

        // Send department list to HTML
        model.addAttribute("departments", departments);

        // Send count to HTML
        model.addAttribute("totalDepartments", totalDepartments);

        return "Department/showDepartmentDirector";
    }


}