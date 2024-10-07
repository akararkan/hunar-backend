package com.Hunar_factory.api.quarry_api;


import com.Hunar_factory.enums.OrderStatus;
import com.Hunar_factory.model.quarry.QuarryOrder;
import com.Hunar_factory.repo.quarry_repo.QuarryOrderRepository;
import com.Hunar_factory.service.quarry_service.QuarryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quarry-orders")
@RequiredArgsConstructor
public class QuarryOrderController {
    private final QuarryOrderService quarryOrderService;
    private final QuarryOrderRepository quarryOrderRepository;

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<QuarryOrder> getQuarryOrder(@PathVariable Long id) {
        return quarryOrderService.getQuarryOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addOrder")
    public ResponseEntity<QuarryOrder> createQuarryOrder(@RequestBody QuarryOrder quarryOrder) {
        QuarryOrder savedOrder = quarryOrderService.saveQuarryOrder(quarryOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @PutMapping("/updateOrderById/{id}")
    public ResponseEntity<QuarryOrder> updateQuarryOrder(@PathVariable Long id, @RequestBody QuarryOrder quarryOrder) {
        return ResponseEntity.ok(quarryOrderService.updateQuarryOrder(id, quarryOrder));
    }

    @DeleteMapping("/deleteOrderById/{id}")
    public ResponseEntity<Void> deleteQuarryOrder(@PathVariable Long id) {
        quarryOrderService.deleteQuarryOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOrderStatus/{status}")
    public List<QuarryOrder> getOrdersByStatus(@PathVariable OrderStatus status) {
        return quarryOrderService.getQuarryOrdersByStatus(status);
    }

    @GetMapping("/getOrderByEmail/{email}")
    public List<QuarryOrder> getOrdersByOwnerEmail(@PathVariable String email) {
        return quarryOrderService.getQuarryOrdersByOwnerEmail(email);
    }

    @GetMapping("/allOrders")
    public List<QuarryOrder> getAllOrders() {
        return quarryOrderService.getAllQuarryOrders();
    }
}
