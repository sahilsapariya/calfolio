package com.example.calfolio.mapper;

import com.example.calfolio.dto.FinTermDto;
import com.example.calfolio.entity.FinTerm;

import java.util.*;
import java.util.stream.Collectors;

public class FinTermMapper {

    public static FinTermDto toDto(FinTerm entity) {
        return new FinTermDto(
                entity.getId(),
                entity.getInitialLetter(),
                entity.getName(),
                entity.getDescription()
        );
    }

    public static FinTerm toEntity(FinTermDto dto) {
        FinTerm term = new FinTerm();
        term.setId(dto.getId());
        term.setInitialLetter(dto.getInitialLetter());
        term.setName(dto.getName());
        term.setDescription(dto.getDescription());
        return term;
    }

    public static List<FinTermDto> toDtoList(List<FinTerm> entities) {
        return entities.stream().map(FinTermMapper::toDto).collect(Collectors.toList());
    }

    public static Map<String, List<FinTermDto>> toDtoGrouped(Map<String, List<FinTerm>> groupedEntities) {
        return groupedEntities.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> toDtoList(e.getValue())
                ));
    }
}
