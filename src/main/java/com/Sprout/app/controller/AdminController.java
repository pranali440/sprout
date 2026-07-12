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

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private SugarFactoryDataService service;
    @Autowired
    private WorkerService workerService;

    @Value("${root.admin.username}")
    private String rootAdminUsername;

    @Value("${root.admin.password}")
    private String rootAdminPassword;
    
    @GetMapping("/adminlogin")
    public String showLoginForm() {
        return "adminLogin";
    }
    
    @GetMapping("/admindash")
    public String adminDash() {
        return "admin";
    }
    
    @PostMapping("/adminlogin")
    public String adminLogin(@RequestParam String adminId, @RequestParam String adminPassword, Model model) {
       if(adminId.equals(rootAdminUsername) && adminPassword.equals(rootAdminPassword)) {
    	return "redirect:/admin/admindash";
       }else {
    	   model.addAttribute("error", "Invalid username or password. Please try again.");
    	   return "adminLogin";
       }
    }

    
    @GetMapping("/admindash/addAdmin")
    public String addAdmin() {
        return "addAdmin";
    }

    @GetMapping("/admindash/events")
    public String addEvent() {
        return "event";
    }

    @PostMapping("/admindash/addAdmin")
    public String addAdmin(Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/admindash";
    }
    
    @GetMapping("/admindash/pendingWorkers")
    public String pendingWorkers(Model model) {
        model.addAttribute("pendingWorkers", workerService.findPendingWorkers());
        return "pendingWorkers";
    }

    @PostMapping("/admindash/approveWorker/{workerId}")
    public String approveWorker(@PathVariable Integer workerId) {
        workerService.approveWorker(workerId);
        return "redirect:/admin/admindash/pendingWorkers";
    }

    @PostMapping("/admindash/rejectWorker/{workerId}")
    public String rejectWorker(@PathVariable Integer workerId) {
        workerService.rejectWorker(workerId);
        return "redirect:/admin/admindash/pendingWorkers";
    }

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