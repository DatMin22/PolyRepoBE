package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.RoleEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    // Add any additional query methods here if needed
    Optional<RoleEntity> findById(Integer id);


}
