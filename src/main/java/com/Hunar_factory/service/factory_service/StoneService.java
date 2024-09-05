package com.Hunar_factory.service.factory_service;

import com.Hunar_factory.model.factory.Stone;
import com.Hunar_factory.repo.factory_repo.StoneRepository;
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
public class StoneService {
    private final StoneRepository stoneRepository;
    private static final Logger logger = LoggerFactory.getLogger(HelenService.class);


    public ResponseEntity<Stone> addStone(Stone stone) {
        try {
            Stone newStone = Stone.builder()
                    .id(stone.getId())
                    .stoneName(stone.getStoneName())
                    .description(stone.getDescription())
                    .location(stone.getLocation())
                    .color(stone.getColor())
                    .price(stone.getPrice())
                    .createDate(new Date())
                    .updateDate(null)
                    .build();
            stoneRepository.save(newStone);
            return ResponseEntity.ok(newStone);
        }catch (Exception e) {
            // Log the exception
            logger.error("Error occurred while adding Stone", e);
            // Return an appropriate response entity in case of an error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<Stone> updateStone( Long id, Stone stone) {
        Optional<Stone> optionalStone = stoneRepository.findById(id);

        if (optionalStone.isPresent()) {
            Stone existingStone = optionalStone.get();
            // Update fields
            existingStone.setStoneName(stone.getStoneName());
            existingStone.setDescription(stone.getDescription());
            existingStone.setLocation(stone.getLocation());
            existingStone.setType(stone.getType());
            existingStone.setColor(stone.getColor());
            existingStone.setPrice(stone.getPrice());
            existingStone.setUpdateDate(new Date());  // Update the updateDate field

            // Save updated stone
            Stone updatedStone = stoneRepository.save(existingStone);
            return new ResponseEntity<>(updatedStone, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Get all Stones
    public ResponseEntity<List<Stone>> getAllStones() {
        try {
            List<Stone> stones = stoneRepository.findAll();
            return new ResponseEntity<>(stones, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching all Stones", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Stone by ID
    public ResponseEntity<Stone> getStoneById(Long id) {
        try {
            Optional<Stone> optionalStone = stoneRepository.findById(id);
            if (optionalStone.isPresent()) {
                return new ResponseEntity<>(optionalStone.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching Stone by ID", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Stone by ID
    public ResponseEntity<Void> deleteStone(Long id) {
        try {
            Optional<Stone> optionalStone = stoneRepository.findById(id);
            if (optionalStone.isPresent()) {
                stoneRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting Stone", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<Stone>> getAllStonesByColor(String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(stoneRepository.searchByColor(keyword));
    }
}
