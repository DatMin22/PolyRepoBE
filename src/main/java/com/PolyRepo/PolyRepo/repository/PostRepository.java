package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    List<PostEntity> findByCategoryId (int idCategory);
    List<PostEntity> findByUserId(int userid);
//    List<PostEntity> findById (int PostId);
Optional<PostEntity> findById(Integer id);
    @Query("SELECT p FROM posts p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) AND p.poststatus = :poststatus")
    List<PostEntity> findByTitleLikeAndPoststatus(@Param("title") String title, @Param("poststatus") String poststatus);

    List<PostEntity> findAllByPoststatus(String poststatus);
//    @Modifying
//    @Query("update posts set poststatus = 'False', comment.commentstatus = 'False', likes.likestatus= 'False' where id = ?1")
//    void updatePostStatusById(Integer id);
}
