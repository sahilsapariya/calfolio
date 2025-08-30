package com.example.calfolio.service;

import com.example.calfolio.dto.FinComicDto;
import com.example.calfolio.entity.FinComic;

import java.io.IOException;
import java.util.List;

public interface FinComicService {
    FinComic createFinComic(FinComicDto finComicDto) throws IOException;
    FinComic getFinComicById(Long id);
    List<FinComic> getAllFinComics();
    String getFinComicPhoto(Long id);
    FinComic updateFinComic(Long id, FinComicDto finComicDto) throws IOException;
    FinComic patchFinComic(Long id, FinComicDto finComicDto) throws IOException;
    void deleteFinComic(Long id);
}