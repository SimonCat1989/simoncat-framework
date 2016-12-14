package com.simoncat.framework.graph.search;

import java.util.List;

import com.simoncat.framework.graph.elements.Graph;
import com.simoncat.framework.graph.elements.Path;
import com.simoncat.framework.graph.elements.PathAttributes;
import com.simoncat.framework.graph.elements.Vertex;

public interface GraphSearcher<VERTEX_VAL, EDGE_VAL> {

	List<Path<VERTEX_VAL, EDGE_VAL>> depthFirstSearch(Graph<VERTEX_VAL, EDGE_VAL> graph, Vertex<VERTEX_VAL> start, Vertex<VERTEX_VAL> destination,
			int maxHops, PathAttributes<VERTEX_VAL, EDGE_VAL> attributes);

	List<Path<VERTEX_VAL, EDGE_VAL>> breadthFirstSearch();
}