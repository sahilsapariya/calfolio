package com.example.calfolio.repository;

import com.example.calfolio.entity.FinComic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinComicRepository extends JpaRepository<FinComic, Long> {

}
