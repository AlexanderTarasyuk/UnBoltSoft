package com.UnboltSoft.nodes;

import com.UnboltSoft.nodes.model.CustomNode;
import com.UnboltSoft.nodes.model.NodeDto;
import com.UnboltSoft.nodes.model.NodeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NodesController {

    private final NodesService nodesService;
    private final NodeMapper nodeMapper;

    @PostMapping("/nodes")
    public ResponseEntity createNode(@RequestBody(required = true) NodeDto nodeDto) {
        Mono<CustomNode> customNode =
                nodesService.createNode(nodeMapper.mapToCustomNode(nodeDto));
        log.info("Create  node   {} ", customNode.toString());
        return ResponseEntity.ok().body(customNode);
    }

    @GetMapping("/nodes")
    public ResponseEntity<Flux<CustomNode>> getNodes(
            final @RequestParam(name = "page", defaultValue = "0") int page,
            final @RequestParam(name = "size", defaultValue = "10") int size) {
        Flux<CustomNode> customNode = nodesService.findAll(PageRequest.of(page, size));
        log.info("Get  nodes   {} ", customNode.map(CustomNode::toString));
        return ResponseEntity.ok().body(customNode);
    }
}
