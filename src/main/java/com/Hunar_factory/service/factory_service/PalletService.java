package com.Hunar_factory.service.factory_service;

import com.Hunar_factory.enums.StoneName;
import com.Hunar_factory.model.factory.Package;
import com.Hunar_factory.model.factory.Pallet;
import com.Hunar_factory.model.factory.Worker;
import com.Hunar_factory.repo.factory_repo.PackageRepository;
import com.Hunar_factory.repo.factory_repo.PalletRepository;
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
public class PalletService {
    private final PalletRepository palletRepository;
    private static final Logger logger = LoggerFactory.getLogger(PalletService.class);
    private final PackageRepository packageRepository;
    private final WorkerRepository workerRepository;
    public ResponseEntity<Pallet> addPallet(Pallet pallet , Long packageId , Long workerId) {
        Package palletPackage = packageRepository.findById(packageId).orElse(null);
        if(palletPackage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Worker worker = workerRepository.findById(workerId).orElse(null);
        if(worker == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            Pallet newPallet = Pallet.builder()
                    .stoneName(pallet.getStoneName())
                    .weight(pallet.getWeight())
                    .stoneSize(pallet.getStoneSize())
                    .meter(pallet.getMeter())
                    .meterMount(pallet.getMeterMount())
                    .price(pallet.getPrice())
                    .worker(pallet.getWorker())
                    .createDate(new Date())
                    .aPackage(palletPackage)
                    .worker(worker)
                    .updateDate(null)
                    .build();

            Pallet savedPallet = palletRepository.save(newPallet);
            return ResponseEntity.ok(savedPallet);
        } catch (Exception e) {
            // Log the exception
            logger.error("Error occurred while adding Pallet", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Pallet> updatePallet(Long id, Pallet pallet) {
        try {
            Optional<Pallet> existingPalletOptional = palletRepository.findById(id);

            if (existingPalletOptional.isPresent()) {
                Pallet existingPallet = existingPalletOptional.get();
                existingPallet.setStoneName(pallet.getStoneName());
                existingPallet.setWeight(pallet.getWeight());
                existingPallet.setStoneSize(pallet.getStoneSize());
                existingPallet.setMeter(pallet.getMeter());
                existingPallet.setMeterMount(pallet.getMeterMount());
                existingPallet.setPrice(pallet.getPrice());
                existingPallet.setWorker(pallet.getWorker());
                existingPallet.setAPackage(pallet.getAPackage());
                existingPallet.setUpdateDate(new Date());

                Pallet updatedPallet = palletRepository.save(existingPallet);
                return ResponseEntity.ok(updatedPallet);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            // Log the exception
            logger.error("Error occurred while updating Pallet", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public void deletePalletById(Long id) {
        palletRepository.deleteById(id);
    }

    public ResponseEntity<Pallet> getPalletById(Long id) {
        Optional<Pallet> palletOptional = palletRepository.findById(id);
        return palletOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<List<Pallet>> getAllPallets() {
        return ResponseEntity.ok(palletRepository.findAll());
    }


}
