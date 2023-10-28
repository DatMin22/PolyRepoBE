package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
