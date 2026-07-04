package com.Sprout.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sprout.app.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    Admin findByAdminId(String adminId);
}

