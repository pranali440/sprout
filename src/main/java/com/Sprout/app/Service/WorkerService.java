package com.Sprout.app.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Worker;
import com.Sprout.app.Entity.LoginRequest;
import com.Sprout.app.repository.WorkerRepository;


@Service
public class WorkerService {
	 
    @Autowired
    private WorkerRepository workerRepository;
    
    public Integer registerWorker(Worker worker) {
    	 if (worker.getEmail() == null || worker.getEmail().isEmpty()) {
             throw new IllegalArgumentException("Email is required.");
         }
        Worker savedWorker = workerRepository.save(worker);
        return savedWorker.getWorkerId();
    }
    
    public ResponseEntity<?> login(LoginRequest loginRequest) {
    	String email = loginRequest.getEmail();
    	System.out.println(email);
        String password = loginRequest.getPassword();

        Worker worker = workerRepository.findByEmail(email);

        if (worker != null && worker.getPassword().equals(password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
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



