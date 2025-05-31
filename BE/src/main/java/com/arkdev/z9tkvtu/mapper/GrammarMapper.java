package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.request.GrammarRequest;
import com.arkdev.z9tkvtu.dto.response.GrammarResponse;
import com.arkdev.z9tkvtu.model.Grammar;
import com.arkdev.z9tkvtu.model.Media;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GrammarMapper {
    public Grammar toGrammar(GrammarRequest request) {
        if (request == null) return null;
        Grammar grammar = new Grammar();
        grammar.setGrammarName(request.getGrammarName());
        grammar.setGrammarText(request.getGrammarText());
        return grammar;
    }

    public void updateGrammar(Grammar grammar,  GrammarRequest request) {
        if (grammar == null || request == null) return;
        grammar.setGrammarName(request.getGrammarName());
        grammar.setGrammarText(request.getGrammarText());
    }

    public GrammarResponse  toGrammarResponse(Grammar grammar) {
        if (grammar == null) return null;
        return new GrammarResponse(
                grammar.getGrammarName(),
                grammar.getGrammarText(),
                Optional.ofNullable(grammar.getMedia())
                        .map(Media::getMediaType).orElse(null),
                Optional.ofNullable(grammar.getMedia())
                        .map(Media::getUrl).orElse(null)
        );
    }
}
