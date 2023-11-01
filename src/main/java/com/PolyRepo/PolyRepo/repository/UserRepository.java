package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
//    @Query("from users where email = ?1")
//    List<UserEntity> getUserByEmail(String email);

    UserEntity findByEmail(String email);
    @Query("SELECT u FROM Users u WHERE lower(u.username) LIKE lower(concat('%', :query, '%')) OR lower(u.email) LIKE lower(concat('%', :query, '%'))")
    List<UserEntity> searchUserByNameOrEmail(@Param("query") String query);
}
