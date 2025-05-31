package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.response.VocabularyWordResponse;
import com.arkdev.z9tkvtu.model.VocabularyWord;
import org.springframework.stereotype.Component;

@Component
public class VocabularyWordMapper {
    public VocabularyWordResponse toVocabularyWordResponse(VocabularyWord vocabularyWord) {
        if (vocabularyWord == null) return null;
        return new VocabularyWordResponse(
                vocabularyWord.getWord(),
                vocabularyWord.getMeaning()
        );
    }
}
