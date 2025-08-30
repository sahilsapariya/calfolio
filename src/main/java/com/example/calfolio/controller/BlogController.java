package com.example.calfolio.controller;

import com.example.calfolio.dto.BlogDto;
import com.example.calfolio.entity.Blog;
import com.example.calfolio.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<Blog> createBlog(@ModelAttribute BlogDto blogDTO) throws IOException {
        Blog savedBlog = blogService.createBlog(blogDTO);
        return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }
    
    @GetMapping("/{id}/photo")
    public ResponseEntity<Map<String, String>> getBlogPhoto(@PathVariable Long id) {
        String imageBase64 = blogService.getBlogPhoto(id);
    
        Map<String, String> response = Map.of("image", imageBase64);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(
            @PathVariable Long id,
            @ModelAttribute BlogDto blogDto
    ) throws IOException {
        Blog updatedBlog = blogService.updateBlog(id, blogDto);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Blog> patchBlog(
            @PathVariable Long id,
            @ModelAttribute BlogDto patchDto
    ) throws IOException {
        Blog updatedBlog = blogService.patchBlog(id, patchDto);
        return ResponseEntity.ok(updatedBlog);
    }

}
