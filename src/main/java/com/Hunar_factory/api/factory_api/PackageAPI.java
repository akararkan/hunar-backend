package com.Hunar_factory.api.factory_api;

import com.Hunar_factory.model.factory.Package;
import com.Hunar_factory.service.factory_service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/package")
@RequiredArgsConstructor
public class PackageAPI {

    private final PackageService packageService;

    // Add Package
    @PostMapping("/addPackage")
    public ResponseEntity<Package> addPackage(@RequestBody Package packageData , @RequestParam Long orderId) {
        return packageService.addPackage(packageData , orderId);
    }

    // Update Package
    @PutMapping("/updatePackageById/{id}")
    public ResponseEntity<Package> updatePackage(@PathVariable Long id, @RequestBody Package packageData) {
        return packageService.updatePackage(id, packageData);
    }

    // Get all Packages
    @GetMapping("/allPackages")
    public ResponseEntity<List<Package>> getAllPackages() {
        return packageService.getAllPackages();
    }

    // Get Package by ID
    @GetMapping("/getPackageById/{id}")
    public ResponseEntity<Package> getPackageById(@PathVariable Long id) {
        return packageService.getPackageById(id);
    }

    // Delete Package by ID
    @DeleteMapping("/deletePackageById/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
        return packageService.deletePackage(id);
    }
}
