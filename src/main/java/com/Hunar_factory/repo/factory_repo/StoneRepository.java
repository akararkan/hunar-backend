package com.Hunar_factory.repo.factory_repo;

import com.Hunar_factory.enums.StoneName;
import com.Hunar_factory.model.factory.Stone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoneRepository extends JpaRepository<Stone, Long> {

    @Query("select s from Stone s where s.color LIKE %:keyword% ")
    List<Stone> searchByColor(@Param("keyword") String keyword);
}
