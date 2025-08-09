package com.example.calfolio.repository;

import com.example.calfolio.entity.FinTerm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinTermRepository extends JpaRepository<FinTerm, Long> {
    List<FinTerm> findByInitialLetterIn(List<String> letters);
}