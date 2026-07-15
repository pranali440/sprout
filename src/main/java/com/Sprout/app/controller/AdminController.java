package com.Sprout.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Sprout.app.Entity.Admin;
import com.Sprout.app.Entity.SugarFactoryData;
import com.Sprout.app.Service.AdminService;
import com.Sprout.app.Service.SugarFactoryDataService;
import com.Sprout.app.Service.WorkerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private SugarFactoryDataService service;

    @Value("${root.admin.username}")
    private String rootAdminUsername;

    @Value("${root.admin.password}")
    private String rootAdminPassword;
    
    @GetMapping("/adminlogin")
    public String showLoginForm() {
        return "adminLogin";
    }
    
    @GetMapping("/admindash")
    public String adminDash(HttpSession session) {
        if (!Boolean.TRUE.equals(session.getAttribute("rootAdmin"))) {
            return "redirect:/admin/adminlogin";
        }
        return "admin";
    }
    
    @PostMapping("/adminlogin")
    public String adminLogin(@RequestParam String adminId, @RequestParam String adminPassword, Model model, HttpSession session) {
       if(adminId.equals(rootAdminUsername) && adminPassword.equals(rootAdminPassword)) {
    	session.setAttribute("rootAdmin", true);
    	return "redirect:/admin/admindash";
       }else {
    	   model.addAttribute("error", "Invalid username or password. Please try again.");
    	   return "adminLogin";
       }
    }

    // Creating a new department admin is a root-admin-only action: this is what
    // replaces hardcoding a separate username/password per department. Only
    // someone already authenticated as the root admin can reach these two routes.

    @GetMapping("/admindash/addAdmin")
    public String addAdmin(HttpSession session) {
        if (!Boolean.TRUE.equals(session.getAttribute("rootAdmin"))) {
            return "redirect:/admin/adminlogin";
        }
        return "addAdmin";
    }

    @GetMapping("/admindash/events")
    public String addEvent(HttpSession session) {
        if (!Boolean.TRUE.equals(session.getAttribute("rootAdmin"))) {
            return "redirect:/admin/adminlogin";
        }
        return "event";
    }

    @PostMapping("/admindash/addAdmin")
    public String addAdmin(Admin admin, HttpSession session) {
        if (!Boolean.TRUE.equals(session.getAttribute("rootAdmin"))) {
            return "redirect:/admin/adminlogin";
        }
        adminService.saveAdmin(admin);
        return "redirect:/admin/admindash";
    }

    // Pending-worker approval is now handled entirely by each department's own
    // admin at /department/pendingWorkers (see PageController) - the root admin
    // no longer sees a combined, cross-department queue.

    @GetMapping("/analytics")
    public String index(Model model) {
        return " ";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("data", new SugarFactoryData());
        return "analytics_data";
    }

    @PostMapping("/add_data")
    public String addData(SugarFactoryData data) {
        
        service.save(data);
        
        return "redirect:/admin/admindash";
    }
}