package com.simoncat.framework.graph.elements.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.simoncat.framework.graph.elements.Edge;
import com.simoncat.framework.graph.elements.Graph;
import com.simoncat.framework.graph.elements.Vertex;

@Slf4j
abstract class AbsGraph<VERTEX_VAL, EDGE_VAL> implements Graph<VERTEX_VAL, EDGE_VAL> {

	private final Edge<VERTEX_VAL, EDGE_VAL> PLACEHOLDER_EDGE = new EdgeImpl<>(null, null, null);

	private Table<Vertex<VERTEX_VAL>, Vertex<VERTEX_VAL>, Edge<VERTEX_VAL, EDGE_VAL>> graph;

	AbsGraph() {
		graph = HashBasedTable.create();
	}

	Table<Vertex<VERTEX_VAL>, Vertex<VERTEX_VAL>, Edge<VERTEX_VAL, EDGE_VAL>> getGraph() {
		return graph;
	}

	@Override
	public int getSizeOfVertexs() {
		return graph.rowKeySet().size();
	}

	@Override
	public int getSizeOfEdges() {
		return graph.values().size() - getSizeOfVertexs();
	}

	@Override
	public Optional<Edge<VERTEX_VAL, EDGE_VAL>> getEdge(Vertex<VERTEX_VAL> startVertex, Vertex<VERTEX_VAL> endVertex) {
		if (Objects.isNull(startVertex) || Objects.isNull(endVertex)) {
			log.error("Detect NULL when get edge with vertex: from {} to {}.", startVertex, endVertex);
			return Optional.empty();
		}

		log.debug("Start to get edge from vertex {} to vertex {}.", startVertex, endVertex);
		Edge<VERTEX_VAL, EDGE_VAL> result = graph.get(startVertex, endVertex);
		if (PLACEHOLDER_EDGE == result) {
			log.debug("Detect Place Holder from vertex {} to vertex {}.", startVertex, endVertex);
			return Optional.empty();
		}
		return Optional.ofNullable(result);
	}

	@Override
	public Optional<Vertex<VERTEX_VAL>> getVertexByName(String vertexName) {
		if (StringUtils.isBlank(vertexName)) {
			log.error("Detect NULL when get vertex by vertex name: {}.", vertexName);
			return Optional.empty();
		}
		return graph.rowKeySet().stream().filter(vertex -> vertex.getName().equals(vertexName)).findFirst();
	}

	@Override
	public Optional<Vertex<VERTEX_VAL>> getVertexByValue(VERTEX_VAL vertexValue) {
		if (Objects.isNull(vertexValue)) {
			log.error("Detect NULL when get vertex by vertex value: {}.", vertexValue);
			return Optional.empty();
		}
		return graph.rowKeySet().stream().filter(vertex -> vertex.getValue().equals(vertexValue)).findFirst();
	}

	@Override
	public void insertNewVertex(Vertex<VERTEX_VAL> newVertex) {
		if (Objects.isNull(newVertex)) {
			log.error("Detect NULL when insert vertex: {}.", newVertex);
		} else {
			graph.put(newVertex, newVertex, PLACEHOLDER_EDGE);
		}
	}

	@Override
	public void insertNewVertexes(Collection<Vertex<VERTEX_VAL>> newVertexes) {
		if (Objects.isNull(newVertexes)) {
			log.error("Detect NULL when insert vertex list: {}.", newVertexes);
		} else {
			newVertexes.stream().forEach(this::insertNewVertex);
		}
	}

	@Override
	public void insertNewEdge(Vertex<VERTEX_VAL> startVertex, Vertex<VERTEX_VAL> endVertex, Edge<VERTEX_VAL, EDGE_VAL> newEdge) {
		if (Objects.isNull(startVertex) || Objects.isNull(endVertex) || Objects.isNull(newEdge)) {
			log.error("Detect NULL when insert edge: {} with vertex: from {} to {}.", startVertex, endVertex);
		} else {
			graph.put(startVertex, endVertex, newEdge);
		}
	}

	@Override
	public void deleteEdge(Vertex<VERTEX_VAL> startVertex, Vertex<VERTEX_VAL> endVertex) {
		if (Objects.isNull(startVertex) || Objects.isNull(endVertex)) {
			log.error("Detect NULL when delete edge from {} to {}.", startVertex, endVertex);
		} else {
			graph.remove(startVertex, endVertex);
		}
	}

	@Override
	public void deleteVertex(Vertex<VERTEX_VAL> vertex) {
		if (Objects.isNull(vertex)) {
			log.error("Detect NULL when delete vertex {}.", vertex);
		} else {
			graph.row(vertex).clear();
			graph.column(vertex).clear();
		}
	}

	@Override
	public Collection<Edge<VERTEX_VAL, EDGE_VAL>> getAllAdjacentEdges(Vertex<VERTEX_VAL> startVertex) {
		if (Objects.isNull(startVertex)) {
			log.error("Detect NULL when get all adjacent edges with vertex {}.", startVertex);
			return Collections.emptyList();
		} else {
			return graph.row(startVertex).values().stream().filter(this::isNonEmptyEdge).collect(Collectors.toList());
		}
	}

	@Override
	public Collection<Vertex<VERTEX_VAL>> getAllVertexes() {
		return graph.rowKeySet();
	}

	@Override
	public Collection<Edge<VERTEX_VAL, EDGE_VAL>> getAllRoutes() {
		return graph.values().stream().filter(this::isNonEmptyEdge).collect(Collectors.toList());
	}

	protected boolean isNonEmptyEdge(Edge<VERTEX_VAL, EDGE_VAL> targetEdge) {
		return targetEdge != PLACEHOLDER_EDGE;
	}
}
