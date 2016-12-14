package com.simoncat.framework.graph.elements.impl;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import com.google.common.collect.Lists;
import com.simoncat.framework.graph.elements.Edge;
import com.simoncat.framework.graph.elements.Path;
import com.simoncat.framework.graph.elements.PathAttributes;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
class PathImpl<VERTEX_VAL, EDGE_VAL> implements Path<VERTEX_VAL, EDGE_VAL> {

	private final List<Edge<VERTEX_VAL, EDGE_VAL>> edges;
	private final PathAttributes<VERTEX_VAL, EDGE_VAL> value;

	PathImpl(PathAttributes<VERTEX_VAL, EDGE_VAL> value) {
		this(Lists.newLinkedList(), value);
	}

	@Override
	public void addEdge(Edge<VERTEX_VAL, EDGE_VAL> edge) {
		Objects.requireNonNull(edge, "Can NOT add a null edge into current Path");

		value.addNewEdge(edge);
		edges.add(edge);
	}

	@Override
	public int compareTo(Path<VERTEX_VAL, EDGE_VAL> o) {
		return value.compareTo(o.getValue());
	}
}
