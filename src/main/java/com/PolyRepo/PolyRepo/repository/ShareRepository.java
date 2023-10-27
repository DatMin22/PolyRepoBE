package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.ShareEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository  extends JpaRepository<ShareEntity,Integer> {
}
