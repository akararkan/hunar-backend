package com.Hunar_factory.service.quarry_service;

import com.Hunar_factory.enums.OrderStatus;
import com.Hunar_factory.model.quarry.QuarryOrder;
import com.Hunar_factory.repo.quarry_repo.QuarryOrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuarryOrderService {
    private final QuarryOrderRepository quarryOrderRepository;

    // Create or Save a Quarry Order
    public QuarryOrder saveQuarryOrder(QuarryOrder quarryOrder) {
        String orderCode = RandomStringUtils.randomAlphabetic(6);
        quarryOrder.setOrderCode(orderCode);
        quarryOrder.setCreateDate(new Date());
        quarryOrder.setUpdateDate(new Date());
        return quarryOrderRepository.save(quarryOrder);
    }

    // Retrieve a Quarry Order by ID
    public Optional<QuarryOrder> getQuarryOrderById(Long id) {
        return quarryOrderRepository.findById(id);
    }

    // Retrieve all Quarry Orders
    public List<QuarryOrder> getAllQuarryOrders() {
        return quarryOrderRepository.findAll();
    }

    // Update a Quarry Order by ID
    public QuarryOrder updateQuarryOrder(Long id, QuarryOrder updatedQuarryOrder) {
        Optional<QuarryOrder> existingQuarryOrder = quarryOrderRepository.findById(id);
        if (existingQuarryOrder.isPresent()) {
            QuarryOrder quarryOrder = existingQuarryOrder.get();
            quarryOrder.setOrderCode(updatedQuarryOrder.getOrderCode());
            quarryOrder.setDest(updatedQuarryOrder.getDest());
            quarryOrder.setOwnerName(updatedQuarryOrder.getOwnerName());
            quarryOrder.setOwnerEmail(updatedQuarryOrder.getOwnerEmail());
            quarryOrder.setOwnerPhoneNumber(updatedQuarryOrder.getOwnerPhoneNumber());
            quarryOrder.setStatus(updatedQuarryOrder.getStatus());
            quarryOrder.setQuantity(updatedQuarryOrder.getQuantity());
            quarryOrder.setUpdateDate(new Date());
            return quarryOrderRepository.save(quarryOrder);
        } else {
            throw new RuntimeException("Quarry Order not found with id " + id);
        }
    }

    // Delete a Quarry Order by ID
    public void deleteQuarryOrder(Long id) {
        quarryOrderRepository.deleteById(id);
    }

    // Retrieve Quarry Orders by Status
    public List<QuarryOrder> getQuarryOrdersByStatus(OrderStatus status) {
        return quarryOrderRepository.findByStatus(status);
    }

    // Retrieve Quarry Orders by Owner Email
    public List<QuarryOrder> getQuarryOrdersByOwnerEmail(String email) {
        return quarryOrderRepository.findByOwnerEmail(email);
    }
}
