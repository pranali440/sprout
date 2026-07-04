package com.Sprout.app.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.Sprout.app.Entity.Worker;
	import com.Sprout.app.Entity.LoginRequest;
	import com.Sprout.app.Service.WorkerService;


	@CrossOrigin(origins = "*")
	@RestController
	@RequestMapping("/api/workers")
	public class WorkerController {
	    @Autowired
	    private WorkerService workerService;

	    @PostMapping("/register")
	    public ResponseEntity<Integer> registerWorker(@RequestBody Worker worker) {
	        Integer registrationId = workerService.registerWorker(worker);
	        return ResponseEntity.status(HttpStatus.CREATED).body(registrationId);
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
	    	System.out.println(loginRequest.getEmail());
	        return workerService.login(loginRequest);
	    }
	    
	    @GetMapping("fetch/{workerId}")
	    public ResponseEntity<Worker> getFarmerById(@PathVariable Integer workerId) {
	        Worker worker = workerService.findById(workerId);
	        if (worker != null) {
	            return ResponseEntity.ok(worker);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @PutMapping("update/{workerId}")
	    public ResponseEntity<Worker> updateWorker(@PathVariable Integer workerId, @RequestBody Worker updatedWorker) {
	        Worker worker = workerService.updateWorker(workerId, updatedWorker);
	        return ResponseEntity.ok(worker);
	    }
	}