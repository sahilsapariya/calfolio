package com.example.calfolio.controller;

import com.example.calfolio.dto.FinTermDto;
import com.example.calfolio.entity.FinTerm;
import com.example.calfolio.service.FinTermService;

import jakarta.validation.Valid;

import com.example.calfolio.mapper.FinTermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/fin-terms")
public class FinTermController {

    @Autowired
    private FinTermService finTermService;

    @PostMapping
    public ResponseEntity<FinTermDto> create(@Valid @RequestBody FinTermDto dto) {
        FinTerm finTerm = FinTermMapper.toEntity(dto);
        FinTerm saved = finTermService.create(finTerm);
        return ResponseEntity.status(HttpStatus.CREATED).body(FinTermMapper.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinTermDto> getById(@PathVariable Long id) {
        FinTerm term = finTermService.getById(id);
        return ResponseEntity.ok(FinTermMapper.toDto(term));
    }

    @GetMapping
    public ResponseEntity<List<FinTermDto>> getAll() {
        List<FinTerm> terms = finTermService.getAll();
        return ResponseEntity.ok(FinTermMapper.toDtoList(terms));
    }

    @GetMapping("/group-by")
    public ResponseEntity<Map<String, List<FinTermDto>>> groupByInitialLetters(
            @RequestParam String letters) {

        List<String> letterList = Arrays.stream(letters.split(","))
                                        .map(String::trim)
                                        .filter(s -> !s.isEmpty())
                                        .toList();

        Map<String, List<FinTerm>> grouped = finTermService.getGroupedByInitialLetters(letterList);
        return ResponseEntity.ok(FinTermMapper.toDtoGrouped(grouped));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinTermDto> update(@PathVariable Long id, @RequestBody FinTermDto dto) {
        FinTerm updated = finTermService.update(id, FinTermMapper.toEntity(dto));
        return ResponseEntity.ok(FinTermMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        finTermService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
