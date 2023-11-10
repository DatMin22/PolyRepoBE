package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//<<<<<<< HEAD
//public interface PostRepository extends JpaRepository<PostEntity, Integer> {
//
//    List<PostEntity> findByCategoryId (int idCategory);
////    List<PostEntity> findById (int PostId);
//
//=======
public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    List<PostEntity> findByCategoryId (int idCategory);
//    List<PostEntity> findById (int PostId);

}
