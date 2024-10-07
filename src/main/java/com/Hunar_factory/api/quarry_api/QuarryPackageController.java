package com.Hunar_factory.api.quarry_api;
import com.Hunar_factory.model.quarry.QuarryPackage;
import com.Hunar_factory.repo.quarry_repo.QuarryPackageRepository;
import com.Hunar_factory.service.quarry_service.QuarryPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quarry-packages")
@RequiredArgsConstructor
public class QuarryPackageController {
    private final QuarryPackageService quarryPackageService;
    private final QuarryPackageRepository quarryPackageRepository;

    @PostMapping("/order/{orderId}")
    public ResponseEntity<QuarryPackage> createQuarryPackage(@PathVariable Long orderId, @RequestBody QuarryPackage quarryPackage) {
        QuarryPackage savedPackage = quarryPackageService.saveQuarryPackage(quarryPackage, orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPackage);
    }

    @PutMapping("/update/{id}/order/{orderId}")
    public ResponseEntity<QuarryPackage> updateQuarryPackage(@PathVariable Long id, @PathVariable Long orderId, @RequestBody QuarryPackage quarryPackage) {
        return ResponseEntity.ok(quarryPackageService.updateQuarryPackage(id, quarryPackage, orderId));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<QuarryPackage> getQuarryPackage(@PathVariable Long id) {
        return quarryPackageService.getQuarryPackageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteQuarryPackage(@PathVariable Long id) {
        quarryPackageService.deleteQuarryPackage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getPackage/{orderId}")
    public List<QuarryPackage> getPackagesByOrderId(@PathVariable Long orderId) {
        return quarryPackageService.getPackagesByQuarryOrderId(orderId);
    }
    @GetMapping("/allPackages")
    public List<QuarryPackage> getAllPackages() {
        return quarryPackageRepository.findAll();
    }
}
