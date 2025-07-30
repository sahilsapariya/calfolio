package com.example.calfolio_backend.service;

import com.example.calfolio_backend.dto.BlogDto;
import com.example.calfolio_backend.entity.Blog;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    Blog createBlog(BlogDto blogDTO) throws IOException;
    Blog getBlogById(Long id);
    List<Blog> getAllBlogs();
    String getBlogPhoto(Long id);
    Blog updateBlog(Long id, BlogDto blogDto) throws IOException;
    Blog patchBlog(Long id, BlogDto blogDto) throws IOException;
    void deleteBlog(Long id);


}
