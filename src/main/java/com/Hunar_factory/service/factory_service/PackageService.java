package com.Hunar_factory.service.factory_service;

import com.Hunar_factory.model.factory.FactoryOrder;
import com.Hunar_factory.model.factory.Package;
import com.Hunar_factory.repo.factory_repo.FactoryOrderRepository;
import com.Hunar_factory.repo.factory_repo.PackageRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PackageService {

    private static final Logger logger = LoggerFactory.getLogger(PackageService.class);
    private final PackageRepository packageRepository;
    private final FactoryOrderRepository orderRepository;
    // Add Package using the builder pattern
    public ResponseEntity<Package> addPackage(Package packageData , Long orderId) {
        FactoryOrder order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            Package newPackage = Package.builder()
                    .code(packageData.getCode())
                    .name(packageData.getName())
                    .description(packageData.getDescription())
//                    .pallets(packageData.getPallets())
                    .totalPrice(packageData.getTotalPrice())
                    .createDate(new Date()) // Set the current date as create date
                    .updateDate(null)       // No update date at creation
                    .factoryOrder(order)
                    .build();

            packageRepository.save(newPackage);
            return new ResponseEntity<>(newPackage, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while adding Package", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Package by ID
    public ResponseEntity<Package> updatePackage(Long id, Package packageData) {
        Optional<Package> optionalPackage = packageRepository.findById(id);

        if (optionalPackage.isPresent()) {
            Package existingPackage = optionalPackage.get();
            // Update fields
            existingPackage.setCode(packageData.getCode());
            existingPackage.setName(packageData.getName());
            existingPackage.setDescription(packageData.getDescription());
//            existingPackage.setPallets(packageData.getPallets());
            existingPackage.setTotalPrice(packageData.getTotalPrice());
            existingPackage.setUpdateDate(new Date()); // Set the update date to the current date
            existingPackage.setFactoryOrder(packageData.getFactoryOrder());

            Package updatedPackage = packageRepository.save(existingPackage);
            return new ResponseEntity<>(updatedPackage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all Packages
    public ResponseEntity<List<Package>> getAllPackages() {
        try {
            List<Package> packages = packageRepository.findAll();
            return new ResponseEntity<>(packages, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching all Packages", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Package by ID
    public ResponseEntity<Package> getPackageById(Long id) {
        try {
            Optional<Package> optionalPackage = packageRepository.findById(id);
            return optionalPackage.map(aPackage -> new ResponseEntity<>(aPackage, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            logger.error("Error occurred while fetching Package by ID", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Package by ID
    public ResponseEntity<Void> deletePackage(Long id) {
        try {
            Optional<Package> optionalPackage = packageRepository.findById(id);
            if (optionalPackage.isPresent()) {
                packageRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting Package", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
