package com.Hunar_factory.service.factory_service;

import com.Hunar_factory.model.factory.Worker;
import com.Hunar_factory.model.factory.WorkerData;
import com.Hunar_factory.repo.factory_repo.WorkerDataRepository;
import com.Hunar_factory.repo.factory_repo.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerDataService {

    private static final Logger logger = LoggerFactory.getLogger(WorkerDataService.class);
    private final WorkerDataRepository workerDataRepository;
    private final WorkerRepository workerRepository; // Inject the WorkerRepository

    // Add WorkerData using the builder pattern
    public ResponseEntity<WorkerData> addWorkerData(WorkerData workerData, Long workerId) {
        Worker worker = workerRepository.findById(workerId).orElse(null);
        if (worker == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Return bad request if worker is not found
        }
        try {
            WorkerData newWorkerData = WorkerData.builder()
                    .id(workerData.getId())
                    .todayCutWeight(workerData.getTodayCutWeight())
                    .stoneName(workerData.getStoneName())
                    .stoneSize(workerData.getStoneSize())
                    .todayGainPrice(workerData.getTodayGainPrice())
                    .worker(worker)  // Assign the Worker object here
                    .createDate(new Date())  // Set the create date to the current date
                    .updateDate(null)        // No update date at creation
                    .build();

            workerDataRepository.save(newWorkerData);
            return ResponseEntity.ok(newWorkerData);
        } catch (Exception e) {
            // Log the exception
            logger.error("Error occurred while adding WorkerData", e);
            // Return an appropriate response entity in case of an error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Update WorkerData by ID
    public ResponseEntity<WorkerData> updateWorkerData(Long id, WorkerData workerData) {
        Optional<WorkerData> optionalWorkerData = workerDataRepository.findById(id);

        if (optionalWorkerData.isPresent()) {
            WorkerData existingWorkerData = optionalWorkerData.get();
            // Update fields
            existingWorkerData.setTodayCutWeight(workerData.getTodayCutWeight());
            existingWorkerData.setStoneName(workerData.getStoneName());
            existingWorkerData.setStoneSize(workerData.getStoneSize());
            existingWorkerData.setTodayGainPrice(workerData.getTodayGainPrice());
            existingWorkerData.setWorker(workerData.getWorker());
            existingWorkerData.setUpdateDate(new Date());

            WorkerData updatedWorkerData = workerDataRepository.save(existingWorkerData);
            return new ResponseEntity<>(updatedWorkerData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all WorkerData entries
    public ResponseEntity<List<WorkerData>> getAllWorkerData() {
        try {
            List<WorkerData> workerDataList = workerDataRepository.findAll();
            return new ResponseEntity<>(workerDataList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching all WorkerData", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get WorkerData by ID
    public ResponseEntity<WorkerData> getWorkerDataById(Long id) {
        try {
            Optional<WorkerData> optionalWorkerData = workerDataRepository.findById(id);
            return optionalWorkerData.map(workerData -> new ResponseEntity<>(workerData, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            logger.error("Error occurred while fetching WorkerData by ID", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete WorkerData by ID
    public ResponseEntity<Void> deleteWorkerData(Long id) {
        try {
            Optional<WorkerData> optionalWorkerData = workerDataRepository.findById(id);
            if (optionalWorkerData.isPresent()) {
                workerDataRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting WorkerData", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
