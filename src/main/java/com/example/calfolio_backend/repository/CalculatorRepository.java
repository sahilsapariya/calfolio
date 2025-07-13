package com.example.calfolio_backend.repository;

import com.example.calfolio_backend.entity.Calculator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculatorRepository extends JpaRepository<Calculator, Long> {
}
