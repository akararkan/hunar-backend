package com.Hunar_factory.service.factory_service;

import com.Hunar_factory.model.factory.Helen;
import com.Hunar_factory.model.factory.Worker;
import com.Hunar_factory.repo.factory_repo.HelenRepository;
import com.Hunar_factory.repo.factory_repo.WorkerRepository;
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
public class HelenService {
    private static final Logger logger = LoggerFactory.getLogger(HelenService.class);
    private final HelenRepository helenRepository;
    private final WorkerRepository workerRepository; // Inject the Worker repository
    public ResponseEntity<Helen> addHelen(Helen helen , Long workerId) {
        Worker worker = workerRepository.findById(workerId).orElse(null);
        if (worker == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Return bad request if worker is not found
        }
        try {
            Helen newHelen = Helen.builder()
                    .id(helen.getId())
                    .name(helen.getName())
                    .type(helen.getType())
                    .createDate(new Date())
                    .worker(worker)
                    .updateDate(null)
                    .build();
            helenRepository.save(newHelen);
            return ResponseEntity.ok(newHelen);
        }catch (Exception e){
            // Log the exception
            logger.error("Error occurred while adding Helen", e);
            // Return an appropriate response entity in case of an error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<Helen> updateHelen(Long id, Helen helen) {
        try {
            // Find the existing Helen by id
            Optional<Helen> existingHelenOptional = helenRepository.findById(id);

            if (existingHelenOptional.isPresent()) {
                Helen existingHelen = existingHelenOptional.get();
                // Update the fields with the new values from the helen object
                existingHelen.setName(helen.getName());
                existingHelen.setType(helen.getType());
                existingHelen.setWorker(helen.getWorker());
                existingHelen.setUpdateDate(new Date()); // Set the update date to the current date
                // Save the updated Helen back to the database
                helenRepository.save(existingHelen);
                return ResponseEntity.ok(existingHelen);
            } else {
                // If Helen with the given id does not exist, return a 404 Not Found response
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (Exception e) {
            // Log the exception
            logger.error("Error occurred while updating Helen", e);

            // Return a 500 Internal Server Error response in case of an exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    public void deleteHelenById(Long id) {
        helenRepository.deleteById(id);
    }

    public ResponseEntity<Helen> getHelenById(Long id) {
        Optional<Helen> existingHelenOptional = helenRepository.findById(id);
        return existingHelenOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    public ResponseEntity<List<Helen>> getAllHelen() {
        return ResponseEntity.ok(helenRepository.findAll());
    }
    public ResponseEntity<List<Helen>> searchHelensByKeyword(String keyword) {
        try {
            // Call the custom query method from the repository
            List<Helen> helens = helenRepository.searchByNameOrType(keyword);

            // Return the found Helens with an OK status
            return ResponseEntity.ok(helens);
        } catch (Exception e) {
            // Log the exception
            logger.error("Error occurred while searching for Helens", e);

            // Return an Internal Server Error status if something goes wrong
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }



}
