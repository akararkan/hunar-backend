package com.Hunar_factory.repo.quarry_repo;

import com.Hunar_factory.enums.OrderStatus;
import com.Hunar_factory.model.quarry.QuarryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuarryOrderRepository extends JpaRepository<QuarryOrder, Long> {
    // Custom method to find orders by status
    List<QuarryOrder> findByStatus(OrderStatus status);

    // Custom method to find orders by owner email
    List<QuarryOrder> findByOwnerEmail(String ownerEmail);
}
