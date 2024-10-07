package com.Hunar_factory.api.factory_api;

import com.Hunar_factory.model.factory.WorkerData;
import com.Hunar_factory.service.factory_service.WorkerDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workerData")
@RequiredArgsConstructor
public class WorkerDataAPI {

    private final WorkerDataService workerDataService;

    // Add WorkerData
    @PostMapping("/addWorkerData")
    public ResponseEntity<WorkerData> addWorkerData(@RequestBody WorkerData workerData , @RequestParam Long workerId) {
        return workerDataService.addWorkerData(workerData , workerId);
    }

    // Update WorkerData
    @PutMapping("/updateWorkerDataById/{id}")
    public ResponseEntity<WorkerData> updateWorkerData(@PathVariable Long id, @RequestBody WorkerData workerData) {
        return workerDataService.updateWorkerData(id, workerData);
    }

    // Get all WorkerData entries
    @GetMapping("/allWorkerData")
    public ResponseEntity<List<WorkerData>> getAllWorkerData() {
        return workerDataService.getAllWorkerData();
    }

    // Get WorkerData by ID
    @GetMapping("/getWorkerDataById/{id}")
    public ResponseEntity<WorkerData> getWorkerDataById(@PathVariable Long id) {
        return workerDataService.getWorkerDataById(id);
    }

    // Delete WorkerData by ID
    @DeleteMapping("/deleteWorkerDataById/{id}")
    public ResponseEntity<Void> deleteWorkerData(@PathVariable Long id) {
        return workerDataService.deleteWorkerData(id);
    }
}
