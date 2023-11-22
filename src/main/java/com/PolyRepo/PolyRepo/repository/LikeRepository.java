package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.CategoryEntity;
import com.PolyRepo.PolyRepo.Entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity,Integer> {
    //    List<LikeEntity> findByPostId (int idPost);
    List<LikeEntity> findByUserId(int userid);
    List<LikeEntity> findByPostsId(int postid);
}
