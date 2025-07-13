package com.example.calfolio_backend.repository;

import com.example.calfolio_backend.entity.FinTerm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinTermRepository extends JpaRepository<FinTerm, Long> {
    List<FinTerm> findByInitialLetterIn(List<String> letters);
}