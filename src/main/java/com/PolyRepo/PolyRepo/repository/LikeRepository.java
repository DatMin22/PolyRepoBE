package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.CategoryEntity;
import com.PolyRepo.PolyRepo.Entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity,Integer> {
    //    List<LikeEntity> findByPostId (int idPost);
    List<LikeEntity> findByUserId(int userid);
    List<LikeEntity> findByPostsId(int postid);

    @Query(value = "SELECT id, post_id, user_id, likestatus FROM likes WHERE post_id = :postId AND user_id = :userId", nativeQuery = true)
    List<Object[]> getLikeByPostIdAndUserId(@Param("postId") int postId, @Param("userId") int userId);
}
