package com.UnboltSoft.nodes.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

class NodeMapperTest {
    private NodeMapper nodeMapper = getMapper(NodeMapper.class);

    @Test
    void mapModelToDto() {
        NodeDto source = new NodeDto();
        source.setNodeRoot("root");
        source.setNodeDescription("rootDescription");

        CustomNode result = nodeMapper.mapToCustomNode(source);
        assertNotNull(result);
        assertEquals(source.getNodeRoot(), result.getNodeRoot(),
                "Invalid source nodeRoot");
        assertEquals(source.getNodeDescription(), result.getNodeDescription(),
                "Invalid source nodeDescription");

        assertNotEquals("Exception", "Invalid", result.getNodeRoot());
        assertNotEquals("Exception", "Invalid", result.getNodeDescription());
    }
}