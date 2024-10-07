package com.Hunar_factory.service.quarry_service;

import com.Hunar_factory.model.quarry.Quarry;
import com.Hunar_factory.model.quarry.QuarryWorker;
import com.Hunar_factory.repo.quarry_repo.QuarryRepository;
import com.Hunar_factory.repo.quarry_repo.QuarryWorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuarryWorkerService {
    private final QuarryWorkerRepository quarryWorkerRepository;
    private final QuarryRepository quarryRepository; // For managing quarries

    // Create or Save a Quarry Worker with the associated Quarry
    public QuarryWorker saveQuarryWorker(QuarryWorker quarryWorker, Long quarryId) {
        Optional<Quarry> quarryOptional = quarryRepository.findById(quarryId);
        if (quarryOptional.isPresent()) {
            quarryWorker.setQuarry(quarryOptional.get());
            quarryWorker.setCreateDate(new Date());
            quarryWorker.setUpdateDate(new Date());
            return quarryWorkerRepository.save(quarryWorker);
        } else {
            throw new RuntimeException("Quarry not found with id " + quarryId);
        }
    }

    // Retrieve a Quarry Worker by ID
    public Optional<QuarryWorker> getQuarryWorkerById(Long id) {
        return quarryWorkerRepository.findById(id);
    }

    // Retrieve all Quarry Workers
    public List<QuarryWorker> getAllQuarryWorkers() {
        return quarryWorkerRepository.findAll();
    }

    // Update a Quarry Worker and associate with Quarry
    public QuarryWorker updateQuarryWorker(Long id, QuarryWorker updatedQuarryWorker, Long quarryId) {
        Optional<QuarryWorker> existingQuarryWorker = quarryWorkerRepository.findById(id);
        if (existingQuarryWorker.isPresent()) {
            QuarryWorker quarryWorker = existingQuarryWorker.get();
            Optional<Quarry> quarryOptional = quarryRepository.findById(quarryId);

            if (quarryOptional.isPresent()) {
                quarryWorker.setFullName(updatedQuarryWorker.getFullName());
                quarryWorker.setEmail(updatedQuarryWorker.getEmail());
                quarryWorker.setPhone(updatedQuarryWorker.getPhone());
                quarryWorker.setAddress(updatedQuarryWorker.getAddress());
                quarryWorker.setQuarry(quarryOptional.get()); // Set the associated quarry
                quarryWorker.setUpdateDate(new Date());
                return quarryWorkerRepository.save(quarryWorker);
            } else {
                throw new RuntimeException("Quarry not found with id " + quarryId);
            }
        } else {
            throw new RuntimeException("Quarry Worker not found with id " + id);
        }
    }

    // Delete a Quarry Worker by ID
    public void deleteQuarryWorker(Long id) {
        quarryWorkerRepository.deleteById(id);
    }

    // Retrieve Quarry Workers by Quarry ID
    public List<QuarryWorker> getWorkersByQuarryId(Long quarryId) {
        return quarryWorkerRepository.findByQuarryId(quarryId);
    }
}
