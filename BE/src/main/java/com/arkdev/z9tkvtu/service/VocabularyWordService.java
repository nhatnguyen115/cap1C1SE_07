package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.response.VocabularyWordResponse;
import com.arkdev.z9tkvtu.mapper.VocabularyWordMapper;
import com.arkdev.z9tkvtu.repository.VocabularyWordRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class VocabularyWordService {
    VocabularyWordRepository vocabularyWordRepository;
    VocabularyWordMapper vocabularyWordMapper;

    public List<VocabularyWordResponse> findVocabularyWord(Integer sectionId) {
        return vocabularyWordRepository.findAllBySectionId(sectionId)
                .stream().map(vocabularyWordMapper::toVocabularyWordResponse)
                .toList();
    }
}
