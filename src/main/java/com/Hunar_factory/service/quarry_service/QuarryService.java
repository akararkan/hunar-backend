package com.Hunar_factory.service.quarry_service;

import com.Hunar_factory.model.quarry.Quarry;
import com.Hunar_factory.repo.quarry_repo.QuarryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuarryService {
    private final QuarryRepository quarryRepository;

    // Create or Save a Quarry
    public Quarry saveQuarry(Quarry quarry) {
        quarry.setCreateDate(new Date());
        quarry.setUpdateDate(new Date());
        return quarryRepository.save(quarry);
    }

    // Retrieve a Quarry by ID
    public Optional<Quarry> getQuarryById(Long id) {
        return quarryRepository.findById(id);
    }

    // Retrieve all Quarries
    public List<Quarry> getAllQuarries() {
        return quarryRepository.findAll();
    }

    // Update a Quarry by ID
    public Quarry updateQuarry(Long id, Quarry updatedQuarry) {
        Optional<Quarry> existingQuarry = quarryRepository.findById(id);
        if (existingQuarry.isPresent()) {
            Quarry quarry = existingQuarry.get();
            quarry.setName(updatedQuarry.getName());
            quarry.setDescription(updatedQuarry.getDescription());
            quarry.setLocation(updatedQuarry.getLocation());
            quarry.setType(updatedQuarry.getType());
            quarry.setAvailability(updatedQuarry.isAvailability());
            quarry.setUpdateDate(new Date());
            return quarryRepository.save(quarry);
        } else {
            throw new RuntimeException("Quarry not found with id " + id);
        }
    }

    // Delete a Quarry by ID
    public void deleteQuarry(Long id) {
        quarryRepository.deleteById(id);
    }

    // Retrieve Quarries by availability (custom method)
    public List<Quarry> getQuarriesByAvailability(boolean availability) {
        return quarryRepository.findByAvailability(availability);
    }
}
