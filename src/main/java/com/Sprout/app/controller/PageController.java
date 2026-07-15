package com.Sprout.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Sprout.app.Entity.Admin;
import com.Sprout.app.Entity.Farmer;
import com.Sprout.app.Entity.LoginRequest;
import com.Sprout.app.Entity.Worker;
import com.Sprout.app.Service.AdminService;
import com.Sprout.app.Service.FarmerService;
import com.Sprout.app.Service.WorkerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {
	
	@Autowired
	AdminService adminService;

	@Autowired
	WorkerService workerService;

	@Autowired
	FarmerService farmerService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${root.admin.username}")
	private String rootAdminUsername;

	@Value("${root.admin.password}")
	private String rootAdminPassword;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/aboutus")
    public String aboutUs() {
        return "AboutUs";
    }

    @GetMapping("/contactus")
    public String contactUs() {
        return "Contactus";
    }
    
    @GetMapping("/farmerportaldash")
    public String farmerportal() {
        return "farmerportaldash";
    }
    
    @GetMapping("/deptdashboard")
    public String deptDashboard() {
        return "deptdashboard";
    }
    
    @GetMapping("/factorydash")
    public String factDashboard() {
        return "factorydash";
    }
    
    @GetMapping("/deptlogin")
    public String deptLogin() {
        return "deptLogin";
    }

    @PostMapping("/deptlogin")
    public String login(@RequestParam String adminId, @RequestParam String adminPassword, @RequestParam String department, Model model, HttpSession session) {
        model.addAttribute("adminId", adminId);
        model.addAttribute("department", department);

        // "Admin" isn't a real department in the Admin table - it's the root/super
        // admin, authenticated against the hardcoded credentials instead. Selecting
        // it from the same dropdown just routes the same form to a different check,
        // so there's still only one login page/endpoint for every kind of admin.
        if ("Admin".equalsIgnoreCase(department)) {
            if (adminId.equals(rootAdminUsername) && adminPassword.equals(rootAdminPassword)) {
                session.setAttribute("rootAdmin", true);
                return "redirect:/admin/admindash";
            } else {
                model.addAttribute("error", "Invalid username or password. Please try again.");
                return "deptLogin";
            }
        }

        Admin admin = adminService.findByAdminId(adminId);

        if (admin == null || !passwordEncoder.matches(adminPassword, admin.getAdminPassword())) {
            model.addAttribute("error", "Invalid username or password. Please try again.");
            return "deptLogin";
        }

        if (admin.getDepartment() == null || !admin.getDepartment().equalsIgnoreCase(department)) {
            model.addAttribute("error", "This account isn't registered under the selected department. Please choose the correct department.");
            return "deptLogin";
        }

        // Store the authenticated department in the session instead of relying solely
        // on the URL query parameter, so /department/dashboard can't be reached by
        // simply guessing/editing the URL without having logged in.
        session.setAttribute("adminId", admin.getAdminId());
        session.setAttribute("adminDepartment", admin.getDepartment());

        return "redirect:/department/dashboard?department=" + admin.getDepartment();
    }
    
    @GetMapping("/workerLogin")
    public String workerLoginPage() {
        return "workerLogin";
    }
    
    @PostMapping("/workerlogin")
    public String workerLogin(@RequestParam String email, @RequestParam String password, Model model) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        ResponseEntity<?> response = workerService.login(loginRequest);
        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/deptdashboard";
        } else if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
            model.addAttribute("error", "Your account is still pending admin approval. Please check back later.");
            return "workerLogin";
        } else {
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "workerLogin";
        }
    }

    @GetMapping("/workerRegister")
    public String workerRegisterPage() {
        return "workerRegister";
    }

    @PostMapping("/workerRegister")
    public String workerRegisterForm(@ModelAttribute Worker worker, RedirectAttributes redirectAttributes, Model model) {
        try {
            Integer workerId = workerService.registerWorker(worker);
            redirectAttributes.addFlashAttribute("workerId", workerId);
            return "redirect:/workerRegistrationSuccess";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "This email is already registered. Please log in instead.");
            return "workerRegister";
        }
    }

    @GetMapping("/workerRegistrationSuccess")
    public String workerRegistrationSuccess() {
        return "workerRegistrationSuccess";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "commonLogin";
    }

    @PostMapping("/login")
    public String commonLogin(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        String id = email == null ? "" : email.trim();

        // Try farmer accounts first
        Farmer farmer = farmerService.findByEmail(id);
        if (farmer != null && passwordEncoder.matches(password, farmer.getPassword())) {
            session.setAttribute("farmerEmail", farmer.getEmail());
            session.setAttribute("farmerName", farmer.getName());
            return "redirect:/farmerportaldash";
        }

        // Then try worker accounts
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(id);
        loginRequest.setPassword(password);
        ResponseEntity<?> response = workerService.login(loginRequest);
        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/deptdashboard";
        }

        model.addAttribute("error", "We couldn't find an account matching those details. Please check your email and password.");
        return "commonLogin";
    }

    @GetMapping("/staffLogin")
    public String staffLoginPage() {
        return "staffLogin";
    }

    @GetMapping("/register")
    public String registerLanding() {
        return "registerLanding";
    }
    
    @GetMapping("/department/dashboard")
    public String departmentDashboard(@RequestParam(required = false) String department, HttpSession session) {
        // Fall back to the department stored at login time if the query
        // parameter is missing (direct hit, refresh, bookmark, etc.)
        if (department == null || department.isBlank()) {
            department = (String) session.getAttribute("adminDepartment");
        }

        // No department in the request or the session means the user never
        // logged in through /deptlogin — send them back there instead of
        // throwing a MissingServletRequestParameterException.
        if (department == null) {
            return "redirect:/deptlogin";
        }

        if ("Caneyard".equals(department)) {
            return "caneyard"; 
        } else if ("Electrical".equals(department)) {
        	return "electricaldept";
        } else if ("Crushing".equals(department)) {
        	return "crushing";
        } else if ("Billing".equalsIgnoreCase(department)) {
        	return "billing";
        } else if ("Admin".equalsIgnoreCase(department)) {
            return "redirect:/admin/admindash";
        } else {
            return "redirect:/"; 
        }
    }

    // ---- Department-admin pending worker approvals ----
    // Scoped to whichever department is authenticated in the session, so a
    // Billing admin only ever sees/approves Billing workers, and so on.
    // Root admin no longer sees a combined queue - each department owns its own.

    @GetMapping("/department/pendingWorkers")
    public String departmentPendingWorkers(Model model, HttpSession session) {
        String department = (String) session.getAttribute("adminDepartment");
        if (department == null) {
            return "redirect:/deptlogin";
        }
        model.addAttribute("pendingWorkers", workerService.findPendingWorkersByDept(department));
        model.addAttribute("department", department);
        return "Pendingworkers";
    }

    @PostMapping("/department/approveWorker/{workerId}")
    public String departmentApproveWorker(@PathVariable Integer workerId, HttpSession session) {
        String department = (String) session.getAttribute("adminDepartment");
        if (department == null) {
            return "redirect:/deptlogin";
        }
        workerService.approveWorkerInDept(workerId, department);
        return "redirect:/department/pendingWorkers";
    }

    @PostMapping("/department/rejectWorker/{workerId}")
    public String departmentRejectWorker(@PathVariable Integer workerId, HttpSession session) {
        String department = (String) session.getAttribute("adminDepartment");
        if (department == null) {
            return "redirect:/deptlogin";
        }
        workerService.rejectWorkerInDept(workerId, department);
        return "redirect:/department/pendingWorkers";
    }

    @GetMapping("/admin")
    public String Admin(HttpSession session) {
        if (!Boolean.TRUE.equals(session.getAttribute("rootAdmin"))) {
            return "redirect:/admin/adminlogin";
        }
        return "admin";
    }
    
    @GetMapping("/admin/add_achievements")
    public String Achievements() {
        return "achievements";
    }
}