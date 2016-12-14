package com.simoncat.framework.graph.elements;

import java.util.List;

public interface Edge<VERTEX_VAL, EDGE_VAL> {

	List<Vertex<VERTEX_VAL>> getVertexes();

	Vertex<VERTEX_VAL> getVertexExceptFor(Vertex<VERTEX_VAL> unexpectedVertex);

	EDGE_VAL getValue();

}
