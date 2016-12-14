package com.simoncat.framework.graph.elements;

public interface PathAttributes<VERTEX_VAL, EDGE_VAL> extends Comparable<PathAttributes<VERTEX_VAL, EDGE_VAL>> {

	void addNewEdge(Edge<VERTEX_VAL, EDGE_VAL> edge);

	PathAttributes<VERTEX_VAL, EDGE_VAL> makeCopy();
}
