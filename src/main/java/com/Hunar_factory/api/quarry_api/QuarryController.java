package com.Hunar_factory.api.quarry_api;

import com.Hunar_factory.model.quarry.Quarry;
import com.Hunar_factory.repo.quarry_repo.QuarryRepository;
import com.Hunar_factory.service.quarry_service.QuarryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quarry")
@RequiredArgsConstructor
public class QuarryController {
    private final QuarryService quarryService;
    private final QuarryRepository quarryRepository;

    @GetMapping("/getQuarryById/{id}")
    public ResponseEntity<Quarry> getQuarry(@PathVariable Long id) {
        return quarryService.getQuarryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addQuarry")
    public ResponseEntity<Quarry> createQuarry(@RequestBody Quarry quarry) {
        Quarry savedQuarry = quarryService.saveQuarry(quarry);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuarry);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<Quarry> updateQuarry(@PathVariable Long id, @RequestBody Quarry quarry) {
        return ResponseEntity.ok(quarryService.updateQuarry(id, quarry));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteQuarry(@PathVariable Long id) {
        quarryService.deleteQuarry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByAvailability/{status}")
    public List<Quarry> getQuarriesByAvailability(@PathVariable boolean status) {
        return quarryService.getQuarriesByAvailability(status);
    }

    @GetMapping("/allQuarries")
    public ResponseEntity<List<Quarry>> getAllQuarries(){
        return ResponseEntity.status(HttpStatus.OK).body(quarryRepository.findAll());
    }


}
