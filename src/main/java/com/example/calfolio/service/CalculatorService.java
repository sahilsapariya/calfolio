package com.example.calfolio.service;

import com.example.calfolio.entity.Calculator;
import com.example.calfolio.exception.ResourceNotFoundException;
import com.example.calfolio.repository.CalculatorRepository;
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

    public Calculator update(Long id, Calculator calculatorRequest) {
        Calculator existingCalculator = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculator not found with id " + id));

        // Update fields
        existingCalculator.setCalcName(calculatorRequest.getCalcName());
        existingCalculator.setCalcType(calculatorRequest.getCalcType());
        existingCalculator.setCalcFormula(calculatorRequest.getCalcFormula());
        existingCalculator.setStatus(calculatorRequest.getStatus());
        existingCalculator.setDescription(calculatorRequest.getDescription());

        return repository.save(existingCalculator);
    }
    

    public void delete(Long id) {
        Calculator calc = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculator not found with id: " + id));
        repository.delete(calc);
    }
}
