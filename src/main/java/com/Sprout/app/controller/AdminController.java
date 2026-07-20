package com.Sprout.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Sprout.app.Entity.Admin;
import com.Sprout.app.Entity.Sector;
import com.Sprout.app.Entity.SugarFactoryData;
import com.Sprout.app.Entity.Village;
import com.Sprout.app.Service.AdminService;
import com.Sprout.app.Service.SectorService;
import com.Sprout.app.Service.SugarFactoryDataService;
import com.Sprout.app.Service.VillageService;
import com.Sprout.app.Service.WorkerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private SugarFactoryDataService service;
    @Autowired
    private VillageService villageService;
    @Autowired
    private SectorService sectorService;

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
    public String addAdmin(HttpSession session, Model model) {
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

    // Villages are what farmers pick when booking a pickup, and each village
    // belongs to a sector (used to match nearby owners/drivers). Previously
    // the only way to add either was a raw JSON POST to /api/villages/add or
    // /api/sectors/add - no UI existed to see or manage them at all.
    @GetMapping("/admindash/villages")
    public String manageVillages(HttpSession session, Model model) {
        if (!Boolean.TRUE.equals(session.getAttribute("rootAdmin"))) {
            return "redirect:/admin/adminlogin";
        }
        model.addAttribute("villages", villageService.getAllVillages());
        model.addAttribute("sectors", sectorService.getAllSectors());
        return "manageVillages";
    }

    @PostMapping("/admindash/villages/addSector")
    public String addSector(@RequestParam String sectorName, HttpSession session, Model model) {
        if (!Boolean.TRUE.equals(session.getAttribute("rootAdmin"))) {
            return "redirect:/admin/adminlogin";
        }
        if (sectorName != null && !sectorName.isBlank()) {
            Sector sector = new Sector();
            sector.setName(sectorName.trim());
            sectorService.saveSector(sector);
        }
        return "redirect:/admin/admindash/villages";
    }

    @PostMapping("/admindash/villages/addVillage")
    public String addVillage(@RequestParam String villageName, @RequestParam Long sectorId, HttpSession session, Model model) {
        if (!Boolean.TRUE.equals(session.getAttribute("rootAdmin"))) {
            return "redirect:/admin/adminlogin";
        }
        if (villageName != null && !villageName.isBlank()) {
            Sector sector = new Sector();
            sector.setId(sectorId);
            Village village = new Village();
            village.setName(villageName.trim());
            village.setSector(sector);
            villageService.saveVillage(village);
        }
        return "redirect:/admin/admindash/villages";
    }

    @PostMapping("/admindash/addAdmin")
    public String addAdmin(Admin admin, HttpSession session, Model model) {
        if (!Boolean.TRUE.equals(session.getAttribute("rootAdmin"))) {
            return "redirect:/admin/adminlogin";
        }
        if (admin.getAdminId() == null || admin.getAdminId().isBlank()
                || admin.getAdminPassword() == null || admin.getAdminPassword().isBlank()
                || admin.getDepartment() == null || admin.getDepartment().isBlank()) {
            model.addAttribute("error", "Please fill in all fields.");
            return "addAdmin";
        }
        if (adminService.findByAdminId(admin.getAdminId()) != null) {
            model.addAttribute("error", "That Admin ID is already taken. Please choose a different one.");
            return "addAdmin";
        }
        try {
            adminService.saveAdmin(admin);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "That Admin ID is already taken. Please choose a different one.");
            return "addAdmin";
        }
        model.addAttribute("success", "Department admin account created for " + admin.getAdminId() + " (" + admin.getDepartment() + ").");
        return "addAdmin";
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