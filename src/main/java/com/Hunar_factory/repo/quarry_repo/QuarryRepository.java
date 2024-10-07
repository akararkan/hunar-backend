package com.Hunar_factory.repo.quarry_repo;

import com.Hunar_factory.model.quarry.Quarry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuarryRepository extends JpaRepository<Quarry, Long> {
    // Custom method to find quarries by availability
    List<Quarry> findByAvailability(boolean availability);
}
