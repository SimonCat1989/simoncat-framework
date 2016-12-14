package com.simoncat.framework.graph.elements.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.simoncat.framework.graph.elements.Edge;
import com.simoncat.framework.graph.elements.Vertex;

class UnDirectedGraph<VERTEX_VAL, EDGE_VAL> extends AbsGraph<VERTEX_VAL, EDGE_VAL> {

	@Override
	public int getSizeOfEdges() {
		return (getGraph().values().size() - getSizeOfVertexs()) / 2;
	}

	@Override
	public void insertNewEdge(Vertex<VERTEX_VAL> startVertex, Vertex<VERTEX_VAL> endVertex, Edge<VERTEX_VAL, EDGE_VAL> newEdge) {
		getGraph().put(startVertex, endVertex, newEdge);
		getGraph().put(endVertex, startVertex, newEdge);
	}

	@Override
	public void deleteEdge(Vertex<VERTEX_VAL> startVertex, Vertex<VERTEX_VAL> endVertex) {
		getGraph().remove(startVertex, endVertex);
		getGraph().remove(endVertex, startVertex);
	}

	@Override
	public Collection<Edge<VERTEX_VAL, EDGE_VAL>> getAllRoutes() {
		Stack<Vertex<VERTEX_VAL>> vertexes = new Stack<Vertex<VERTEX_VAL>>();
		vertexes.addAll(getAllVertexes());

		Collection<Edge<VERTEX_VAL, EDGE_VAL>> resultList = Lists.newArrayList();
		while (!vertexes.empty()) {
			Vertex<VERTEX_VAL> currentVertex = vertexes.peek();
			resultList.addAll(vertexes.stream().map(vertex -> getEdge(currentVertex, vertex)).filter(Optional::isPresent).map(Optional::get)
					.filter(edge -> isNonEmptyEdge(edge)).collect(Collectors.toList()));
			vertexes.pop();
		}
		return resultList;
	}
}
