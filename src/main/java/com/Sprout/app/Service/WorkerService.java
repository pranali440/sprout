package com.Sprout.app.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Worker;
import com.Sprout.app.Entity.LoginRequest;
import com.Sprout.app.repository.WorkerRepository;


@Service
public class WorkerService {
	 
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Integer registerWorker(Worker worker) {
    	 if (worker.getEmail() == null || worker.getEmail().isEmpty()) {
             throw new IllegalArgumentException("Email is required.");
         }
        worker.setPassword(passwordEncoder.encode(worker.getPassword()));
        Worker savedWorker = workerRepository.save(worker);
        return savedWorker.getWorkerId();
    }
    
    public ResponseEntity<?> login(LoginRequest loginRequest) {
    	String email = loginRequest.getEmail();
    	System.out.println(email);
        String password = loginRequest.getPassword();

        Worker worker = workerRepository.findByEmail(email);

        if (worker == null || !passwordEncoder.matches(password, worker.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        if (!worker.isApproved()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your account is pending admin approval.");
        }
        return ResponseEntity.ok("Login successful");
    }

    public java.util.List<Worker> findPendingWorkers() {
        return workerRepository.findByApprovedFalse();
    }

    public Worker approveWorker(Integer workerId) {
        Worker worker = workerRepository.findById(workerId).orElse(null);
        if (worker != null) {
            worker.setApproved(true);
            return workerRepository.save(worker);
        }
        return null;
    }

    public void rejectWorker(Integer workerId) {
        workerRepository.deleteById(workerId);
    }
    

    public Worker findById(Integer workerId) {
        return workerRepository.findById(workerId).orElse(null);
    }

    public Worker updateWorker(Integer workerId, Worker updatedWorker) {
        Worker existingWorker = workerRepository.findById(workerId).orElse(null);
        if (existingWorker != null) {
            updatedWorker.setWorkerId(workerId);
            return workerRepository.save(updatedWorker);
        } else {
            throw new RuntimeException("Farmer not found with id: " + workerId);
        }
    }
   }