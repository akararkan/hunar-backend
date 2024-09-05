package com.Hunar_factory.repo.factory_repo;

import com.Hunar_factory.model.factory.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query("select w from Worker w where w.fullName LIKE %:keyword% ")
    List<Worker> searchWorkerByFullName(@Param("keyword") String keyword);
}
