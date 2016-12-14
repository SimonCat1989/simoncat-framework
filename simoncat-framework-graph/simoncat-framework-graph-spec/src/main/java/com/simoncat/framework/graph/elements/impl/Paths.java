package com.simoncat.framework.graph.elements.impl;

import com.google.common.collect.Lists;
import com.simoncat.framework.graph.elements.Path;
import com.simoncat.framework.graph.elements.PathAttributes;

public final class Paths {

	public static <VERTEX_VAL, EDGE_VAL> Path<VERTEX_VAL, EDGE_VAL> newPath() {
		return new PathImpl<VERTEX_VAL, EDGE_VAL>(null);
	}

	public static <VERTEX_VAL, EDGE_VAL, PATH_VAL> Path<VERTEX_VAL, EDGE_VAL> newPath(PathAttributes<VERTEX_VAL, EDGE_VAL> value) {
		return new PathImpl<VERTEX_VAL, EDGE_VAL>(value);
	}

	public static <VERTEX_VAL, EDGE_VAL, PATH_VAL> Path<VERTEX_VAL, EDGE_VAL> clone(Path<VERTEX_VAL, EDGE_VAL> oldPath) {
		return new PathImpl<VERTEX_VAL, EDGE_VAL>(Lists.newLinkedList(oldPath.getEdges()), oldPath.getValue().makeCopy());
	}
}
