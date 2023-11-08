package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
}
