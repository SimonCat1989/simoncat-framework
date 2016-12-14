package com.simoncat.framework.graph.elements;

import java.util.Collection;
import java.util.Optional;

public interface Graph<VERTEX_VAL, EDGE_VAL> {

	int getSizeOfVertexs();

	int getSizeOfEdges();

	Optional<Edge<VERTEX_VAL, EDGE_VAL>> getEdge(Vertex<VERTEX_VAL> startVertex, Vertex<VERTEX_VAL> endVertex);

	Optional<Vertex<VERTEX_VAL>> getVertexByName(String vertexName);

	Optional<Vertex<VERTEX_VAL>> getVertexByValue(VERTEX_VAL vertexValue);

	void insertNewVertex(Vertex<VERTEX_VAL> newVertex);

	void insertNewVertexes(Collection<Vertex<VERTEX_VAL>> newVertexes);

	void insertNewEdge(Vertex<VERTEX_VAL> startVertex, Vertex<VERTEX_VAL> endVertex, Edge<VERTEX_VAL, EDGE_VAL> newEdge);

	void deleteEdge(Vertex<VERTEX_VAL> startVertex, Vertex<VERTEX_VAL> endVertex);

	void deleteVertex(Vertex<VERTEX_VAL> vertex);

	Collection<Edge<VERTEX_VAL, EDGE_VAL>> getAllAdjacentEdges(Vertex<VERTEX_VAL> startVertex);

	Collection<Vertex<VERTEX_VAL>> getAllVertexes();

	Collection<Edge<VERTEX_VAL, EDGE_VAL>> getAllRoutes();
}
