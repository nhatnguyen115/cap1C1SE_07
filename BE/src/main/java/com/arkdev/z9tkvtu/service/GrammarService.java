package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.response.GrammarResponse;
import com.arkdev.z9tkvtu.mapper.GrammarMapper;
import com.arkdev.z9tkvtu.repository.GrammarRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class GrammarService {
    GrammarRepository  grammarRepository;
    GrammarMapper  grammarMapper;

    public List<GrammarResponse> getGrammars(Integer sectionId) {
        return grammarRepository.findBySectionId(sectionId)
                .stream()
                .map(grammarMapper::toGrammarResponse)
                .toList();
    }
}
