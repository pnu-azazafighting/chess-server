package com.example.chessserver.match.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchServiceRouter {
    private final List<MatchService> matchServiceList;

    public MatchService getMatchServiceImpl(String type) {
        return matchServiceList.stream()
                .filter(m-> m.isAvailable(type))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
