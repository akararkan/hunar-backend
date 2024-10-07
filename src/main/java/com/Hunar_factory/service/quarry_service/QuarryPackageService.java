package com.Hunar_factory.service.quarry_service;

import com.Hunar_factory.model.quarry.QuarryPackage;
import com.Hunar_factory.model.quarry.QuarryOrder;
import com.Hunar_factory.repo.quarry_repo.QuarryPackageRepository;
import com.Hunar_factory.repo.quarry_repo.QuarryOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuarryPackageService {
    private final QuarryPackageRepository quarryPackageRepository;
    private final QuarryOrderRepository quarryOrderRepository;

    // Create or Save a Quarry Package for a specific Quarry Order
    public QuarryPackage saveQuarryPackage(QuarryPackage quarryPackage, Long orderId) {
        Optional<QuarryOrder> quarryOrderOptional = quarryOrderRepository.findById(orderId);
        if (quarryOrderOptional.isPresent()) {
            quarryPackage.setQuarryOrder(quarryOrderOptional.get());
            quarryPackage.setCreateDate(new Date());
            quarryPackage.setUpdateDate(new Date());
            return quarryPackageRepository.save(quarryPackage);
        } else {
            throw new RuntimeException("Quarry Order not found with id " + orderId);
        }
    }

    // Retrieve a Quarry Package by ID
    public Optional<QuarryPackage> getQuarryPackageById(Long id) {
        return quarryPackageRepository.findById(id);
    }

    // Retrieve all Quarry Packages
    public List<QuarryPackage> getAllQuarryPackages() {
        return quarryPackageRepository.findAll();
    }

    // Update a Quarry Package by ID
    public QuarryPackage updateQuarryPackage(Long id, QuarryPackage updatedQuarryPackage, Long orderId) {
        Optional<QuarryPackage> existingQuarryPackage = quarryPackageRepository.findById(id);
        if (existingQuarryPackage.isPresent()) {
            QuarryPackage quarryPackage = existingQuarryPackage.get();
            Optional<QuarryOrder> quarryOrderOptional = quarryOrderRepository.findById(orderId);

            if (quarryOrderOptional.isPresent()) {
                quarryPackage.setName(updatedQuarryPackage.getName());
                quarryPackage.setDescription(updatedQuarryPackage.getDescription());
                quarryPackage.setSize(updatedQuarryPackage.getSize());
                quarryPackage.setTripperType(updatedQuarryPackage.getTripperType());
                quarryPackage.setPackageWeight(updatedQuarryPackage.getPackageWeight());
                quarryPackage.setPriceInQuarry(updatedQuarryPackage.getPriceInQuarry());
                quarryPackage.setQuarryOrder(quarryOrderOptional.get()); // Associate with the order
                quarryPackage.setUpdateDate(new Date());
                return quarryPackageRepository.save(quarryPackage);
            } else {
                throw new RuntimeException("Quarry Order not found with id " + orderId);
            }
        } else {
            throw new RuntimeException("Quarry Package not found with id " + id);
        }
    }

    // Delete a Quarry Package by ID
    public void deleteQuarryPackage(Long id) {
        quarryPackageRepository.deleteById(id);
    }

    // Retrieve all Quarry Packages by Quarry Order ID
    public List<QuarryPackage> getPackagesByQuarryOrderId(Long orderId) {
        return quarryPackageRepository.findByQuarryOrderId(orderId);
    }
}
