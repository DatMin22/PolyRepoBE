package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
Optional<PostEntity> findById(Integer id);
    @Query("SELECT p FROM posts p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<PostEntity> findByTitleLike(@Param("title") String title);
}
