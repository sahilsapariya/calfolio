package com.example.calfolio.controller;

import com.example.calfolio.dto.FinComicDto;
import com.example.calfolio.entity.FinComic;
import com.example.calfolio.service.FinComicService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fin-comic")
@RequiredArgsConstructor
public class FinComicController {

    private final FinComicService finComicService;

    // Create a FinComic
    @PostMapping
    public ResponseEntity<FinComic> createFinComic(@ModelAttribute FinComicDto finComicDto) throws IOException {
        FinComic savedFinComic = finComicService.createFinComic(finComicDto);
        return new ResponseEntity<>(savedFinComic, HttpStatus.CREATED);
    }

    // Get all FinComics
    @GetMapping
    public ResponseEntity<List<FinComic>> getAllFinComics() {
        List<FinComic> finComics = finComicService.getAllFinComics();
        return new ResponseEntity<>(finComics, HttpStatus.OK);
    }

    // Get a FinComic by ID
    @GetMapping("/{id}")
    public ResponseEntity<FinComic> getFinComicById(@PathVariable Long id) {
        FinComic finComic = finComicService.getFinComicById(id);
        return new ResponseEntity<>(finComic, HttpStatus.OK);
    }

    // Get FinComic photo by ID
    @GetMapping("/{id}/photo")
    public ResponseEntity<Map<String, String>> getFinComicPhoto(@PathVariable Long id) {
        String imageBase64 = finComicService.getFinComicPhoto(id);
        
        Map<String, String> response = Map.of("image", imageBase64);
        return ResponseEntity.ok(response);
    }

    // Update a FinComic by ID (full update)
    @PutMapping("/{id}")
    public ResponseEntity<FinComic> updateFinComic(
            @PathVariable Long id,
            @ModelAttribute FinComicDto finComicDto
    ) throws IOException {
        FinComic updatedFinComic = finComicService.updateFinComic(id, finComicDto);
        return ResponseEntity.ok(updatedFinComic);
    }

    // Patch a FinComic by ID (partial update)
    @PatchMapping("/{id}")
    public ResponseEntity<FinComic> patchFinComic(
            @PathVariable Long id,
            @ModelAttribute FinComicDto finComicDto
    ) throws IOException {
        FinComic updatedFinComic = finComicService.patchFinComic(id, finComicDto);
        return ResponseEntity.ok(updatedFinComic);
    }

    // Delete a FinComic by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinComic(@PathVariable Long id) {
        finComicService.deleteFinComic(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
