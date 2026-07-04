package com.Sprout.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Admin;
import com.Sprout.app.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin findByAdminId(String adminId) {
        return adminRepository.findByAdminId(adminId);
    }
}

