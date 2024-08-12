package com.week5.Homework.Sohel_Week5_Homework.repositories;

import com.week5.Homework.Sohel_Week5_Homework.entities.SessionEntity;
import com.week5.Homework.Sohel_Week5_Homework.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity,Long> {
    List<SessionEntity> findByUserEntity(UserEntity userEntity);

    Optional<SessionEntity> findByToken(String token);
}
