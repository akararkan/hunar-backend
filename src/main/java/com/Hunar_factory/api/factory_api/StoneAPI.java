package com.Hunar_factory.api.factory_api;

import com.Hunar_factory.model.factory.Stone;
import com.Hunar_factory.service.factory_service.StoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
