package com.Hunar_factory.api.factory_api;

import com.Hunar_factory.model.factory.Stone;
import com.Hunar_factory.service.factory_service.StoneService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stone")
@RequiredArgsConstructor
public class StoneAPI {

    private final StoneService stoneService;

    // Add Stone
    @PostMapping("/addStone")
    public ResponseEntity<Stone> addStone(@RequestBody Stone stone) {
        return stoneService.addStone(stone);
    }

    // Update Stone
    @PutMapping("/updateStoneById/{id}")
    public ResponseEntity<Stone> updateStone(@PathVariable Long id, @RequestBody Stone stone) {
        return stoneService.updateStone(id, stone);
    }

    // Get All Stones
    @GetMapping("/allStones")
    public ResponseEntity<List<Stone>> getAllStones() {
        return stoneService.getAllStones();
    }

    // Get Stone by ID
    @GetMapping("/getStoneById/{id}")
    public ResponseEntity<Stone> getStoneById(@PathVariable Long id) {
        return stoneService.getStoneById(id);
    }

    // Delete Stone by ID
    @DeleteMapping("/deleteStoneById/{id}")
    public ResponseEntity<Void> deleteStone(@PathVariable Long id) {
        return stoneService.deleteStone(id);
    }

    // Get All Stones by Color
    @GetMapping("/searchStoneByColor/{keyword}")
    public ResponseEntity<List<Stone>> getAllStonesByColor(@PathVariable String keyword) {
        return stoneService.getAllStonesByColor(keyword);
    }

    private static final Logger logger = LoggerFactory.getLogger(StoneAPI.class);

    // Define the directory where images will be saved
    private final String UPLOAD_DIR = "uploads/stones/";

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file selected to upload");
        }

        try {
            // Ensure the directory exists
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a unique file name
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            // Save the file to the directory
            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath);

            // Return the file path (URL) to the client
            String fileUrl = "/uploads/stones/" + uniqueFilename; // This is what will be stored in the database
            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {
            logger.error("Error occurred while uploading file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Image upload failed");
        }
    }
}
