package com.Hunar_factory.api.factory_api;

import com.Hunar_factory.model.factory.Pallet;
import com.Hunar_factory.repo.factory_repo.PalletRepository;
import com.Hunar_factory.service.factory_service.PalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pallet")
@RequiredArgsConstructor
public class PalletAPI {

    private final PalletService palletService;

    @PostMapping("/addPallet")
    public ResponseEntity<Pallet> addPallet(@RequestBody Pallet pallet){
        return palletService.addPallet(pallet);
    }

    @PutMapping("/updatePalletById/{id}")
    public ResponseEntity<Pallet> updatePalletById(@PathVariable Long id, @RequestBody Pallet pallet){
        return palletService.updatePallet(id, pallet);
    }
    @GetMapping("/allPallets")
    public ResponseEntity<List<Pallet>> getAllPallets(){
        return palletService.getAllPallets();
    }
    @GetMapping("/getPalletsById")
    public ResponseEntity<Pallet> getPalletById(@PathVariable Long id){
        return palletService.getPalletById(id);
    }
    @DeleteMapping("/deletePalletById/{id}")
    public void deletePalletById(@PathVariable Long id){
         palletService.deletePalletById(id);
    }

}
