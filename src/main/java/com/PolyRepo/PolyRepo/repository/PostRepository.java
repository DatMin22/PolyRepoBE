package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Integer> {

}
