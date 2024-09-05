package com.Hunar_factory.api.factory_api;

import com.Hunar_factory.model.factory.FactoryOrder;
import com.Hunar_factory.service.factory_service.FactoryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/factoryOrder")
@RequiredArgsConstructor
public class FactoryOrderAPI {

    private final FactoryOrderService factoryOrderService;

    // Add FactoryOrder
    @PostMapping("/addOrder")
    public ResponseEntity<FactoryOrder> addOrder(@RequestBody FactoryOrder factoryOrder) {
        return factoryOrderService.addFactoryOrder(factoryOrder);
    }

    // Update FactoryOrder
    @PutMapping("/updateOrderById/{id}")
    public ResponseEntity<FactoryOrder> updateOrder(@PathVariable Long id, @RequestBody FactoryOrder factoryOrder) {
        return factoryOrderService.updateFactoryOrder(id, factoryOrder);
    }

    // Get all FactoryOrders
    @GetMapping("/allOrders")
    public ResponseEntity<List<FactoryOrder>> getAllOrders() {
        return factoryOrderService.getAllFactoryOrders();
    }

    // Get FactoryOrder by ID
    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<FactoryOrder> getOrderById(@PathVariable Long id) {
        return factoryOrderService.getFactoryOrderById(id);
    }

    // Delete FactoryOrder by ID
    @DeleteMapping("/deleteOrderById/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        return factoryOrderService.deleteFactoryOrder(id);
    }
}
