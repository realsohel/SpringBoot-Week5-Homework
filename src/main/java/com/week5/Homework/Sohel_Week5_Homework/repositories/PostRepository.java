package com.week5.Homework.Sohel_Week5_Homework.repositories;


import com.week5.Homework.Sohel_Week5_Homework.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
