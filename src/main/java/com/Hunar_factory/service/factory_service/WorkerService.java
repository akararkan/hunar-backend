package com.Hunar_factory.service.factory_service;

import com.Hunar_factory.model.factory.Worker;
import com.Hunar_factory.repo.factory_repo.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
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
public class WorkerService {
    private final WorkerRepository workerRepository;
    private static final Logger logger = LoggerFactory.getLogger(WorkerService.class);
    public ResponseEntity<Worker> addWorker(Worker worker) {
        try {
            Worker newWorker = Worker.builder()
                    .id(worker.getId())
                    .fullName(worker.getFullName())
                    .phoneNumber(worker.getPhoneNumber())
                    .address(worker.getAddress())
                    .nationality(worker.getNationality())
                    .workerType(worker.getWorkerType())
                    .createDate(new Date())
                    .updateDate(null)
                    .build();

            // Save the worker and return the response
            Worker savedWorker = workerRepository.save(newWorker);
            return ResponseEntity.ok(savedWorker);
        } catch (Exception e) {
            // Log the exception with an error message
            logger.error("Error occurred while adding Worker", e);
            // Return a 500 Internal Server Error response with a null body
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    public ResponseEntity<Worker> updateWorker(Long id, Worker worker) {
        try {
            // Retrieve the existing worker by ID
            Optional<Worker> existingWorkerOptional = workerRepository.findById(id);

            if (existingWorkerOptional.isPresent()) {
                Worker existingWorker = existingWorkerOptional.get();
                // Update the fields with new values from the provided worker object
                existingWorker.setFullName(worker.getFullName());
                existingWorker.setPhoneNumber(worker.getPhoneNumber());
                existingWorker.setAddress(worker.getAddress());
                existingWorker.setNationality(worker.getNationality());
                existingWorker.setWorkerType(worker.getWorkerType());
                existingWorker.setUpdateDate(new Date()); // Set the update date to the current date
                // Save the updated worker back to the repository
                Worker updatedWorker = workerRepository.save(existingWorker);
                // Return the updated worker in the response
                return ResponseEntity.ok(updatedWorker);
            } else {
                // If the worker with the given ID does not exist, return a 404 Not Found response
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            // Log the exception with an error message
            logger.error("Error occurred while updating Worker", e);

            // Return a 500 Internal Server Error response with a null body
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    public void deleteWorkerById(Long id) {
        workerRepository.deleteById(id);
    }
    public ResponseEntity<Worker> getWorkerById(Long id) {
        Optional<Worker> workerOptional = workerRepository.findById(id);
        return workerOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    public ResponseEntity<List<Worker>> getAllWorkers() {
        return ResponseEntity.ok(workerRepository.findAll());
    }
    public ResponseEntity<List<Worker>> searchWorkerByFullName(String key) {
        return ResponseEntity.status(HttpStatus.OK).body(workerRepository.searchWorkerByFullName(key));
    }

}
