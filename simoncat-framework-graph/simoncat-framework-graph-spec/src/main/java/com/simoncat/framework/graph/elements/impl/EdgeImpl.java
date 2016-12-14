package com.simoncat.framework.graph.elements.impl;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.simoncat.framework.graph.elements.Edge;
import com.simoncat.framework.graph.elements.Vertex;

@Data
@AllArgsConstructor
@EqualsAndHashCode
class EdgeImpl<VERTEX_VAL, EDGE_VAL> implements Edge<VERTEX_VAL, EDGE_VAL> {

	private Vertex<VERTEX_VAL> start;
	private Vertex<VERTEX_VAL> next;
	private EDGE_VAL value;

	@Override
	public List<Vertex<VERTEX_VAL>> getVertexes() {
		return Arrays.asList(start, next);
	}

	@Override
	public Vertex<VERTEX_VAL> getVertexExceptFor(Vertex<VERTEX_VAL> unexpectedVertex) {
		return start.equals(unexpectedVertex) ? next : start;
	}
}
