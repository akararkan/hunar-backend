package com.Hunar_factory.repo.quarry_repo;

import com.Hunar_factory.model.quarry.QuarryWorker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuarryWorkerRepository extends JpaRepository<QuarryWorker, Long> {
    List<QuarryWorker> findByQuarryId(Long quarryId);
}
