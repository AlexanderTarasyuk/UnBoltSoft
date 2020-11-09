package com.UnboltSoft.nodes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "nodes")
public class CustomNode {
    @Id
    private String id;
    @NonNull
    private String nodeRoot;
    private String nodeDescription;
}
