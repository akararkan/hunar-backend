package com.Hunar_factory.api.factory_api;

import com.Hunar_factory.service.factory_service.HelenService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Hunar_factory.model.factory.Helen;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/helen")
@RequiredArgsConstructor
public class HelenAPI {
    private final HelenService helenService;
    @PostMapping("/addHelen")
    public ResponseEntity<Helen> addHelen(@RequestBody Helen helen) {
        return helenService.addHelen(helen);
    }

    @PutMapping("/updateHelenById/{id}")
    public ResponseEntity<Helen> updateHelen(@PathVariable Long id, @RequestBody Helen helen) {
        return helenService.updateHelen(id, helen);
    }

    @DeleteMapping("/deleteHelenById/{id}")
    public ResponseEntity<Void> deleteHelenById(@PathVariable Long id) {
        helenService.deleteHelenById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getHelenById/{id}")
    public ResponseEntity<Helen> getHelenById(@PathVariable Long id) {
        return helenService.getHelenById(id);
    }

    @GetMapping("/getHelens")
    public ResponseEntity<List<Helen>> getAllHelen() {
        return helenService.getAllHelen();
    }

    @GetMapping("/searchForHelens")
    public ResponseEntity<List<Helen>> searchHelensByKeyword(@RequestParam String keyword) {
        return helenService.searchHelensByKeyword(keyword);
    }
}
