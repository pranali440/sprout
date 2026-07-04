package com.Sprout.app.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Owner;
import com.Sprout.app.Entity.Sector;
import com.Sprout.app.repository.OwnerRepository;




@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }
    
    public List<Owner> findOwnersBySector(Sector sector) {
        return ownerRepository.findBySector(sector);
    }
    

	public Owner findById(Long ownerId) {
		return ownerRepository.findById(ownerId).orElse(null);
	}
	
	

    
}
