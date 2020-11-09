package com.UnboltSoft.nodes;

import com.UnboltSoft.nodes.model.CustomNode;
import com.UnboltSoft.nodes.model.NodeDto;
import com.UnboltSoft.nodes.model.NodeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NodesControllerTest {
    @Mock
    private NodesService nodesService;
    @Mock
    private NodeMapper nodeMapper;
    @InjectMocks
    private NodesController controller;
    private NodeDto nodeDto;
    private CustomNode expectedNode;

    @BeforeEach
    public void setUp(){
        nodeDto=new NodeDto();
        nodeDto.setNodeRoot("nodeRoot");
        expectedNode =new CustomNode();
        expectedNode.setNodeRoot("nodeRoot");
        given(nodeMapper.mapToCustomNode(nodeDto)).willReturn(expectedNode);
    }

    @Test
    public void shouldCreateNodeAndReturnStatusOK() {
        CustomNode actualNode =nodeMapper.mapToCustomNode(nodeDto);
        assertEquals(expectedNode, actualNode);

        Mono<CustomNode> customNodeMono=Mono.just(actualNode);
        given(nodesService.createNode(nodeMapper.mapToCustomNode(nodeDto))).willReturn(customNodeMono);
        assertEquals(customNodeMono, controller.createNode(nodeDto).getBody());

        ResponseEntity responseEntity =
                controller.createNode(nodeDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Invalid status code");
    }

    @Test
    public void shouldReturnListOfNodesAndReturnStatusOk() {
        CustomNode actualNode =nodeMapper.mapToCustomNode(nodeDto);
        assertEquals(expectedNode, actualNode);

        Flux<CustomNode> customFluxNodes =Flux.just(actualNode);
        given(nodesService.findAll(PageRequest.of(0,10))).willReturn(customFluxNodes);
        assertEquals(customFluxNodes, controller.getNodes(0,10).getBody());

        ResponseEntity<Flux<CustomNode>> responseEntity =
                controller.getNodes(0,10);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Invalid status code");
    }
}
