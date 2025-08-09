package com.example.calfolio.service.impl;

import com.example.calfolio.dto.BlogDto;
import com.example.calfolio.entity.Blog;
import com.example.calfolio.entity.User;
import com.example.calfolio.exception.ResourceNotFoundException;
import com.example.calfolio.repository.BlogRepository;
import com.example.calfolio.service.BlogService;
import com.example.calfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserService userService;

    @Override
    public Blog createBlog(BlogDto blogDTO) throws IOException {
        String encodedImage = null;
        if (blogDTO.getPhoto() != null && !blogDTO.getPhoto().isEmpty()) {
            byte[] bytes = blogDTO.getPhoto().getBytes();
            encodedImage = Base64.getEncoder().encodeToString(bytes);
        }

        User author = userService.getUserById(blogDTO.getAuthorId());
        if (author == null) {
            throw new ResourceNotFoundException("Author not found with id: " + blogDTO.getAuthorId());
        }

        Blog blog = Blog.builder()
                .title(blogDTO.getTitle())
                .content(blogDTO.getContent())
                .author(author)
                .status(blogDTO.getStatus())
                .photoBase64(encodedImage)
                .build();

        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public String getBlogPhoto(Long id) {
        Blog blog = getBlogById(id);
        String base64 = blog.getPhotoBase64();

        if (base64 == null || base64.isEmpty()) {
            throw new ResourceNotFoundException("No photo found for blog with id: " + id);
        }

        String mimeType = "image/jpeg"; // or "image/png"
        return "data:" + mimeType + ";base64," + base64;
    }

    @Override
    public Blog updateBlog(Long id, BlogDto blogDto) throws IOException {
        Blog existingBlog = getBlogById(id);

        // Optional: Allow only admin to update
        if (!existingBlog.getAuthor().getRole().equalsIgnoreCase("admin")) {
            throw new RuntimeException("Only admin can update the blog");
        }

        existingBlog.setTitle(blogDto.getTitle());
        existingBlog.setContent(blogDto.getContent());
        existingBlog.setStatus(blogDto.getStatus());

        if (blogDto.getPhoto() != null && !blogDto.getPhoto().isEmpty()) {
            byte[] bytes = blogDto.getPhoto().getBytes();
            String encodedImage = Base64.getEncoder().encodeToString(bytes);
            existingBlog.setPhotoBase64(encodedImage);
        }

        return blogRepository.save(existingBlog);
    }

    @Override
    public Blog patchBlog(Long id, BlogDto patchDto) throws IOException {
        Blog existingBlog = getBlogById(id);

        if (patchDto.getTitle() != null) existingBlog.setTitle(patchDto.getTitle());
        if (patchDto.getContent() != null) existingBlog.setContent(patchDto.getContent());
        if (patchDto.getStatus() != null) existingBlog.setStatus(patchDto.getStatus());

        if (patchDto.getAuthorId() != null) {
            User author = userService.getUserById(patchDto.getAuthorId());
            existingBlog.setAuthor(author);
        }

        if (patchDto.getPhoto() != null && !patchDto.getPhoto().isEmpty()) {
            byte[] bytes = patchDto.getPhoto().getBytes();
            String encoded = Base64.getEncoder().encodeToString(bytes);
            existingBlog.setPhotoBase64(encoded);
        }

        return blogRepository.save(existingBlog);
    }

    @Override
    public void deleteBlog(Long id) {
        Blog blog = getBlogById(id);
        blogRepository.delete(blog);
    }
}
