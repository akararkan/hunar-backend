package com.Hunar_factory.repo.factory_repo;

import com.Hunar_factory.model.factory.Helen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HelenRepository extends JpaRepository<Helen, Long> {
    // Custom query to search by name or type
    @Query("SELECT h FROM Helen h WHERE h.name LIKE %:keyword% OR h.type LIKE %:keyword%")
    List<Helen> searchByNameOrType(@Param("keyword") String keyword);
}
