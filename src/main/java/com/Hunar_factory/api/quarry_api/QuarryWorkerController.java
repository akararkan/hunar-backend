package com.Hunar_factory.api.quarry_api;

import com.Hunar_factory.model.quarry.QuarryWorker;
import com.Hunar_factory.repo.quarry_repo.QuarryWorkerRepository;
import com.Hunar_factory.service.quarry_service.QuarryWorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quarry-workers")
@RequiredArgsConstructor
public class QuarryWorkerController {
    private final QuarryWorkerService quarryWorkerService;
    private final QuarryWorkerRepository quarryWorkerRepository;

    @PostMapping("/quarry/{quarryId}")
    public ResponseEntity<QuarryWorker> createQuarryWorker(@PathVariable Long quarryId, @RequestBody QuarryWorker quarryWorker) {
        QuarryWorker savedWorker = quarryWorkerService.saveQuarryWorker(quarryWorker, quarryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWorker);
    }

    @PutMapping("/update/{id}/quarry/{quarryId}")
    public ResponseEntity<QuarryWorker> updateQuarryWorker(@PathVariable Long id, @PathVariable Long quarryId, @RequestBody QuarryWorker quarryWorker) {
        return ResponseEntity.ok(quarryWorkerService.updateQuarryWorker(id, quarryWorker, quarryId));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<QuarryWorker> getQuarryWorker(@PathVariable Long id) {
        return quarryWorkerService.getQuarryWorkerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteQuarryWorker(@PathVariable Long id) {
        quarryWorkerService.deleteQuarryWorker(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getWorker/{quarryId}")
    public List<QuarryWorker> getWorkersByQuarry(@PathVariable Long quarryId) {
        return quarryWorkerService.getWorkersByQuarryId(quarryId);
    }

    @GetMapping("/allWorkers")
    public List<QuarryWorker> getAllWorkers() {
        return quarryWorkerRepository.findAll();
    }
}
