package com.example.calfolio_backend.service;

import com.example.calfolio_backend.entity.Calculator;
import com.example.calfolio_backend.exception.ResourceNotFoundException;
import com.example.calfolio_backend.repository.CalculatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {

    private final CalculatorRepository repository;

    public CalculatorService(CalculatorRepository repository) {
        this.repository = repository;
    }

    public List<Calculator> getAll() {
        return repository.findAll();
    }

    public Calculator getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculator not found with id: " + id));
    }

    public Calculator save(Calculator calculator) {
        Calculator saved = repository.save(calculator);
        return saved;
    }
    

    public void delete(Long id) {
        Calculator calc = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculator not found with id: " + id));
        repository.delete(calc);
    }
}
