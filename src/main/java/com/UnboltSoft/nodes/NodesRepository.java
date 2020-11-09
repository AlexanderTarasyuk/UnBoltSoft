package com.UnboltSoft.nodes;

import com.UnboltSoft.nodes.model.CustomNode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface NodesRepository extends ReactiveSortingRepository<CustomNode,
        String> {

    Flux<CustomNode> findAll(final PageRequest pageRequest);
}
