package com.Hunar_factory.api.factory_api;

import com.Hunar_factory.model.factory.Worker;
import com.Hunar_factory.service.factory_service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/worker")
@RequiredArgsConstructor
public class WorkerAPI {
    private final WorkerService workerService;

    @GetMapping("/allWorkers")
    ResponseEntity<List<Worker>> getAllWorkers(){
        return workerService.getAllWorkers();
    }
    @GetMapping("/getWorkerById/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Long id){
        return workerService.getWorkerById(id);
    }
    @GetMapping("/searchWorkerByFullName")
    public ResponseEntity<List<Worker>> searchWorkerByFullName(@RequestParam String key) {
        return workerService.searchWorkerByFullName(key);
    }
    @PostMapping("/addWorker")
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker){
        return workerService.addWorker(worker);
    }
    @PutMapping("/updateWorkerById/{id}")
    ResponseEntity<Worker> updateWorker(@PathVariable Long id,@RequestBody Worker worker){
        return workerService.updateWorker(id , worker);
    }
    @DeleteMapping("/deleteWorkerById/{id}")
    public void deleteWorkerById(@PathVariable Long id) {
        workerService.deleteWorkerById(id);
    }
}
