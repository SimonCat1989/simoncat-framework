package com.simoncat.framework.graph.elements;

import java.util.List;

public interface Path<VERTEX_VAL, EDGE_VAL> extends Comparable<Path<VERTEX_VAL, EDGE_VAL>> {

	List<Edge<VERTEX_VAL, EDGE_VAL>> getEdges();

	PathAttributes<VERTEX_VAL, EDGE_VAL> getValue();

	void addEdge(Edge<VERTEX_VAL, EDGE_VAL> edge);

}
