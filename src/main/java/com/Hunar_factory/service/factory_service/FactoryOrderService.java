package com.Hunar_factory.service.factory_service;

import com.Hunar_factory.model.factory.FactoryOrder;
import com.Hunar_factory.repo.factory_repo.FactoryOrderRepository;
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
public class FactoryOrderService {

    private static final Logger logger = LoggerFactory.getLogger(FactoryOrderService.class);
    private final FactoryOrderRepository factoryOrderRepository;

    // Add FactoryOrder using the builder pattern
    public ResponseEntity<FactoryOrder> addFactoryOrder(FactoryOrder factoryOrderData) {
        try {
            FactoryOrder newFactoryOrder = FactoryOrder.builder()
                    .orderCode(factoryOrderData.getOrderCode())
                    .description(factoryOrderData.getDescription())
                    .dest(factoryOrderData.getDest())
                    .ownerName(factoryOrderData.getOwnerName())
                    .ownerEmail(factoryOrderData.getOwnerEmail())
                    .ownerPhoneNumber(factoryOrderData.getOwnerPhoneNumber())
                    .status(factoryOrderData.getStatus())
                    .createDate(new Date()) // Set the current date as create date
                    .updateDate(null)       // No update date at creation
                    .build();

            factoryOrderRepository.save(newFactoryOrder);
            return new ResponseEntity<>(newFactoryOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while adding FactoryOrder", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update FactoryOrder by ID
    public ResponseEntity<FactoryOrder> updateFactoryOrder(Long id, FactoryOrder factoryOrderData) {
        Optional<FactoryOrder> optionalFactoryOrder = factoryOrderRepository.findById(id);

        if (optionalFactoryOrder.isPresent()) {
            FactoryOrder existingFactoryOrder = optionalFactoryOrder.get();
            // Update fields
            existingFactoryOrder.setOrderCode(factoryOrderData.getOrderCode());
            existingFactoryOrder.setDescription(factoryOrderData.getDescription());
            existingFactoryOrder.setDest(factoryOrderData.getDest());
            existingFactoryOrder.setOwnerName(factoryOrderData.getOwnerName());
            existingFactoryOrder.setOwnerEmail(factoryOrderData.getOwnerEmail());
            existingFactoryOrder.setOwnerPhoneNumber(factoryOrderData.getOwnerPhoneNumber());
            existingFactoryOrder.setStatus(factoryOrderData.getStatus());
            existingFactoryOrder.setUpdateDate(new Date()); // Set the update date to the current date

            FactoryOrder updatedFactoryOrder = factoryOrderRepository.save(existingFactoryOrder);
            return new ResponseEntity<>(updatedFactoryOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all FactoryOrders
    public ResponseEntity<List<FactoryOrder>> getAllFactoryOrders() {
        try {
            List<FactoryOrder> factoryOrders = factoryOrderRepository.findAll();
            return new ResponseEntity<>(factoryOrders, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching all FactoryOrders", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get FactoryOrder by ID
    public ResponseEntity<FactoryOrder> getFactoryOrderById(Long id) {
        try {
            Optional<FactoryOrder> optionalFactoryOrder = factoryOrderRepository.findById(id);
            return optionalFactoryOrder.map(factoryOrder -> new ResponseEntity<>(factoryOrder, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            logger.error("Error occurred while fetching FactoryOrder by ID", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete FactoryOrder by ID
    public ResponseEntity<Void> deleteFactoryOrder(Long id) {
        try {
            Optional<FactoryOrder> optionalFactoryOrder = factoryOrderRepository.findById(id);
            if (optionalFactoryOrder.isPresent()) {
                factoryOrderRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting FactoryOrder", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
