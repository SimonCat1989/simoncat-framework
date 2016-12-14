package com.simoncat.framework.graph.search.impl;

import java.util.Set;

import com.google.common.collect.Sets;
import com.simoncat.framework.graph.elements.Edge;
import com.simoncat.framework.graph.elements.Vertex;
import com.simoncat.framework.graph.elements.impl.Paths;

final class GraphSearchStatusWrappers {

	static <VERTEX_VAL, EDGE_VAL> GraphSearchStatusWrapper<VERTEX_VAL, EDGE_VAL> newStatus(GraphSearchStatusWrapper<VERTEX_VAL, EDGE_VAL> old,
			Vertex<VERTEX_VAL> currentVertex, Edge<VERTEX_VAL, EDGE_VAL> currentEdge) {
		Set<String> visisted = Sets.newHashSet(old.getVisitedSites());
		visisted.add(currentVertex.getName());
		GraphSearchStatusWrapper<VERTEX_VAL, EDGE_VAL> newStatus = new GraphSearchStatusWrapper<VERTEX_VAL, EDGE_VAL>(Paths.clone(old
				.getVisitedPath()), visisted, currentVertex);
		newStatus.addNewVisitedEdge(currentEdge);
		return newStatus;
	}
}
