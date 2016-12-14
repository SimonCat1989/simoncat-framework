package com.simoncat.framework.graph.elements;

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.simoncat.framework.core.tuple.Couple;

public interface GraphFactory {

	<V, E> Graph<V, E> createEmptyDirectedGraph();

	<V, E> Graph<V, E> createDirectedGraph(Map<String, V> vertexes, Map<Pair<String, String>, E> edges);

	<V, E> Graph<V, E> createDirectedGraph(Map<Pair<V, V>, E> data);

	<V, E> Graph<V, E> createEmptyUnDirectedGraph();

	<V, E> Graph<V, E> createUnDirectedGraph(Map<String, V> vertexes, Map<Couple<String>, E> edges);

	<V, E> Graph<V, E> createUnDirectedGraph(Map<Couple<V>, E> data);
}
