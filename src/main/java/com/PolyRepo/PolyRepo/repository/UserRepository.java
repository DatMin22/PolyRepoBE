package com.PolyRepo.PolyRepo.repository;

import com.PolyRepo.PolyRepo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
//    @Query("from users where email = ?1")
//    List<UserEntity> getUserByEmail(String email);

    UserEntity findByEmail(String email);
}
