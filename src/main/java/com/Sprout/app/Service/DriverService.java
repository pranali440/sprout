package com.Sprout.app.Service;




import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Driver;
import com.Sprout.app.Entity.Owner;
import com.Sprout.app.repository.DriverRepository;


@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }
    
    public List<Driver> findByOwner(Owner owner) {
        return driverRepository.findByOwner(owner);
    }
    
    public Optional<Driver> findByName(String name) {
        return driverRepository.findByName(name);
    }

    public void update(Driver driver) {
        driverRepository.save(driver);
    }

	public Driver findById(Integer driverId) {
		return driverRepository.findById(driverId).orElse(null);
	}
}


