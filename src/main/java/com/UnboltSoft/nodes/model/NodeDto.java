package com.UnboltSoft.nodes.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NodeDto {
    private String nodeRoot;
    private String nodeDescription;
}
