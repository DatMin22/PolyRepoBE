package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;



import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository


public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    // Add any additional query methods here if needed
    Optional<RoleEntity> findById(Integer id);


}
