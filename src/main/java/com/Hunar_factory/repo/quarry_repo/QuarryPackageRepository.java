package com.Hunar_factory.repo.quarry_repo;

import com.Hunar_factory.model.quarry.QuarryPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuarryPackageRepository extends JpaRepository<QuarryPackage, Long> {
    List<QuarryPackage> findByQuarryOrderId(Long orderId);
}
