package com.Sprout.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Owner;
import com.Sprout.app.Entity.Tractor;
import com.Sprout.app.repository.TractorRepository;

@Service
public class TractorService {

    private final TractorRepository tractorRepository;

    public TractorService(TractorRepository tractorRepository) {
        this.tractorRepository = tractorRepository;
    }

    public Tractor saveTractor(Tractor tractor) {
        return tractorRepository.save(tractor);
    }
    
    public List<Tractor> findByOwner(Owner owner) {
        return tractorRepository.findByOwner(owner);
    }
    
    public Optional<Tractor> findByName(String name) {
        return tractorRepository.findByName(name);
    }

    public void update(Tractor tractor) {
        tractorRepository.save(tractor);
    }

	public Tractor findById(Integer tractorId) {
		return tractorRepository.findById(tractorId).orElse(null);
	}
}

