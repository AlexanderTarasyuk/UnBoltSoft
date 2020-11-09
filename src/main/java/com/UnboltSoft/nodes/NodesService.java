package com.UnboltSoft.nodes;

import com.UnboltSoft.nodes.model.CustomNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NodesService {

    private NodesRepository nodesRepository;
    private static final int DELAY_PER_ITEM_MS = 100;

    public Flux<CustomNode> findAll(final PageRequest pageRequest) {
        return nodesRepository.findAll(pageRequest).delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }

    public Mono<CustomNode> createNode(final CustomNode node) {
        return nodesRepository.save(node);
    }
}
