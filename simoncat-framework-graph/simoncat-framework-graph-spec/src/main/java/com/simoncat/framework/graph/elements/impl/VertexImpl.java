package com.simoncat.framework.graph.elements.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.simoncat.framework.graph.elements.Vertex;

@Data
@AllArgsConstructor
@EqualsAndHashCode
class VertexImpl<VERTEX_VAL> implements Vertex<VERTEX_VAL> {

	private final String name;
	private final VERTEX_VAL value;
}
