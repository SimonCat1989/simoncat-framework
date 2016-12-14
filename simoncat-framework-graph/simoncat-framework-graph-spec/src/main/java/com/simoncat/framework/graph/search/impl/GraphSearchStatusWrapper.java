package com.simoncat.framework.graph.search.impl;

import java.util.Objects;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.simoncat.framework.graph.elements.Edge;
import com.simoncat.framework.graph.elements.Path;
import com.simoncat.framework.graph.elements.Vertex;

@Data
@AllArgsConstructor
public class GraphSearchStatusWrapper<VERTEX_VAL, EDGE_VAL> {

	private Path<VERTEX_VAL, EDGE_VAL> visitedPath;
	private Set<String> visitedSites;
	private Vertex<VERTEX_VAL> currentVertex;

	GraphSearchStatusWrapper<VERTEX_VAL, EDGE_VAL> addNewVisitedEdge(Edge<VERTEX_VAL, EDGE_VAL> visitedEdge) {
		Objects.requireNonNull(visitedEdge, "Can NOT add a null edge into current Path");

		this.visitedPath.addEdge(visitedEdge);
		return this;
	}

	boolean isVisited(Vertex<VERTEX_VAL> toBeCheckedVertex) {
		return visitedSites.contains(toBeCheckedVertex.getName());
	}

}
