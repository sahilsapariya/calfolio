package com.example.calfolio.service;

import com.example.calfolio.entity.FinTerm;
import com.example.calfolio.exception.ResourceNotFoundException;
import com.example.calfolio.repository.FinTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FinTermService {

    @Autowired
    private FinTermRepository finTermRepository;

    public FinTerm create(FinTerm term) {
        return finTermRepository.save(term);
    }

    @Transactional(readOnly = true)
    public FinTerm getById(Long id) {
        return finTermRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FinTerm not found with id " + id));
    }

    @Transactional(readOnly = true)
    public List<FinTerm> getAll() {
        return finTermRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Map<String, List<FinTerm>> getGroupedByInitialLetters(List<String> letters) {
        List<FinTerm> terms = finTermRepository.findByInitialLetterIn(letters);

        return letters.stream()
                .collect(Collectors.toMap(
                    letter -> letter,
                    letter -> terms.stream()
                                .filter(term -> letter.equalsIgnoreCase(term.getInitialLetter()))
                                .collect(Collectors.toList())
                ));
    }
    
    public FinTerm update(Long id, FinTerm updated) {
        FinTerm existing = getById(id);
        existing.setInitialLetter(updated.getInitialLetter());
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        return finTermRepository.save(existing);
    }

    public void delete(Long id) {
        FinTerm existing = getById(id);
        finTermRepository.delete(existing);
    }
}
