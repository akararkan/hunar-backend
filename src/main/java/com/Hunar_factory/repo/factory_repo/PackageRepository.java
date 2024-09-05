package com.Hunar_factory.repo.factory_repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.Hunar_factory.model.factory.Package;

public interface PackageRepository extends JpaRepository<Package, Long> {
}
