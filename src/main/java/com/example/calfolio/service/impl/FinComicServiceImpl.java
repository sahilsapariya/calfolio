package com.example.calfolio.service.impl;

import com.example.calfolio.dto.FinComicDto;
import com.example.calfolio.entity.FinComic;
import com.example.calfolio.exception.ResourceNotFoundException;
import com.example.calfolio.repository.FinComicRepository;
import com.example.calfolio.service.FinComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinComicServiceImpl implements FinComicService {

    private final FinComicRepository finComicRepository;

    @Override
    public FinComic createFinComic(FinComicDto finComicDto) throws IOException {
        String encodedPhoto = null;
        if (finComicDto.getPhoto() != null && !finComicDto.getPhoto().isEmpty()) {
            byte[] bytes = finComicDto.getPhoto().getBytes();
            encodedPhoto = Base64.getEncoder().encodeToString(bytes);
        }

        String encodedSourceData = null;
        if (finComicDto.getSourceData() != null && !finComicDto.getSourceData().isEmpty()) {
            byte[] bytes = finComicDto.getSourceData().getBytes();
            encodedSourceData = Base64.getEncoder().encodeToString(bytes);
        }

        FinComic finComic = new FinComic();
        finComic.setName(finComicDto.getName());
        finComic.setDescription(finComicDto.getDescription());
        finComic.setPhotoBase64(encodedPhoto);
        finComic.setStatus(finComicDto.getStatus());
        finComic.setAuthor(finComicDto.getAuthor());
        finComic.setSourceData(encodedSourceData);

        return finComicRepository.save(finComic);
    }

    @Override
    public FinComic getFinComicById(Long id) {
        return finComicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FinComic not found with id: " + id));
    }

    @Override
    public List<FinComic> getAllFinComics() {
        return finComicRepository.findAll();
    }

    @Override
    public String getFinComicPhoto(Long id) {
        FinComic finComic = getFinComicById(id);
        String base64 = finComic.getPhotoBase64();

        if (base64 == null || base64.isEmpty()) {
            throw new ResourceNotFoundException("No photo found for FinComic with id: " + id);
        }

        String mimeType = "image/jpeg"; // You might want to determine this dynamically
        return "data:" + mimeType + ";base64," + base64;
    }

    @Override
    public FinComic updateFinComic(Long id, FinComicDto finComicDto) throws IOException {
        FinComic existingFinComic = getFinComicById(id);

        existingFinComic.setName(finComicDto.getName());
        existingFinComic.setDescription(finComicDto.getDescription());
        existingFinComic.setStatus(finComicDto.getStatus());
        existingFinComic.setAuthor(finComicDto.getAuthor());

        // Handle photo update
        if (finComicDto.getPhoto() != null && !finComicDto.getPhoto().isEmpty()) {
            byte[] bytes = finComicDto.getPhoto().getBytes();
            String encodedPhoto = Base64.getEncoder().encodeToString(bytes);
            existingFinComic.setPhotoBase64(encodedPhoto);
        }

        // Handle source data update
        if (finComicDto.getSourceData() != null && !finComicDto.getSourceData().isEmpty()) {
            byte[] bytes = finComicDto.getSourceData().getBytes();
            String encodedSourceData = Base64.getEncoder().encodeToString(bytes);
            existingFinComic.setSourceData(encodedSourceData);
        }

        return finComicRepository.save(existingFinComic);
    }

    @Override
    public FinComic patchFinComic(Long id, FinComicDto finComicDto) throws IOException {
        FinComic existingFinComic = getFinComicById(id);

        // Only update non-null fields
        if (finComicDto.getName() != null) existingFinComic.setName(finComicDto.getName());
        if (finComicDto.getDescription() != null) existingFinComic.setDescription(finComicDto.getDescription());
        if (finComicDto.getStatus() != null) existingFinComic.setStatus(finComicDto.getStatus());
        if (finComicDto.getAuthor() != null) existingFinComic.setAuthor(finComicDto.getAuthor());

        // Handle photo patch
        if (finComicDto.getPhoto() != null && !finComicDto.getPhoto().isEmpty()) {
            byte[] bytes = finComicDto.getPhoto().getBytes();
            String encodedPhoto = Base64.getEncoder().encodeToString(bytes);
            existingFinComic.setPhotoBase64(encodedPhoto);
        }

        // Handle source data patch
        if (finComicDto.getSourceData() != null && !finComicDto.getSourceData().isEmpty()) {
            byte[] bytes = finComicDto.getSourceData().getBytes();
            String encodedSourceData = Base64.getEncoder().encodeToString(bytes);
            existingFinComic.setSourceData(encodedSourceData);
        }

        return finComicRepository.save(existingFinComic);
    }

    @Override
    public void deleteFinComic(Long id) {
        FinComic finComic = getFinComicById(id);
        finComicRepository.delete(finComic);
    }
}
