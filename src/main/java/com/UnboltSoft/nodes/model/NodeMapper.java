package com.UnboltSoft.nodes.model;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface NodeMapper {

    CustomNode mapToCustomNode(NodeDto nodeDto);
}
