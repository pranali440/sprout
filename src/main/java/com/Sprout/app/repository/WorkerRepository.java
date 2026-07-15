package com.Sprout.app.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Sprout.app.Entity.Worker;



@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    Worker findByEmail(String email); 
    Optional<Worker> findById(Integer workerId);
    java.util.List<Worker> findByApprovedFalse();

    // Can't use a derived query name here (e.g. findByApprovedFalseAndDeptName):
    // the Worker entity's field is literally named "DeptName" (capital D), and
    // since @Id is on a field, JPA uses field access and needs that exact name -
    // not the "deptName" Spring Data would normally derive. Explicit JPQL avoids
    // the mismatch.
    @Query("SELECT w FROM Worker w WHERE w.approved = false AND w.DeptName = :deptName")
    java.util.List<Worker> findByApprovedFalseAndDeptName(@Param("deptName") String deptName);
}