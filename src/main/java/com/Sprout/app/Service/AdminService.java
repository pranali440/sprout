package com.Sprout.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Admin;
import com.Sprout.app.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveAdmin(Admin admin) {
        admin.setAdminPassword(passwordEncoder.encode(admin.getAdminPassword()));
        adminRepository.save(admin);
    }

    public Admin findByAdminId(String adminId) {
        return adminRepository.findByAdminId(adminId);
    }
}