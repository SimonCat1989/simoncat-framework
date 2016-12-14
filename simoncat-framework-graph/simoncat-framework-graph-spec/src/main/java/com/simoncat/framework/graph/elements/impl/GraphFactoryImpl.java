package com.simoncat.framework.graph.elements.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.simoncat.framework.core.tuple.Couple;
import com.simoncat.framework.graph.elements.Graph;
import com.simoncat.framework.graph.elements.GraphFactory;
import com.simoncat.framework.graph.elements.Vertex;

public class GraphFactoryImpl implements GraphFactory {

	@Override
	public <V, E> Graph<V, E> createEmptyDirectedGraph() {
		return new DirectedGraph<V, E>();
	}

	@Override
	public <V, E> Graph<V, E> createDirectedGraph(Map<String, V> vertexes, Map<Pair<String, String>, E> edges) {
		return doCreateGraph(vertexes, edges, new DirectedGraph<V, E>());
	}

	@Override
	public <V, E> Graph<V, E> createDirectedGraph(Map<Pair<V, V>, E> data) {
		return doCreateGraph(data, new DirectedGraph<V, E>());
	}

	@Override
	public <V, E> Graph<V, E> createEmptyUnDirectedGraph() {
		return new UnDirectedGraph<V, E>();
	}

	private <V, E> Graph<V, E> doCreateGraph(Map<String, V> vertexes, Map<Pair<String, String>, E> edges, Graph<V, E> graph) {
		Map<String, Vertex<V>> vertexList = doInsertVertexes(vertexes, graph);
		doInsertEdges(vertexList, edges, graph);
		return graph;
	}

	private <V, E> Map<String, Vertex<V>> doInsertVertexes(Map<String, V> vertexes, Graph<V, E> graph) {
		Map<String, Vertex<V>> vertexList = vertexes.entrySet().stream()
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> new VertexImpl<V>(entry.getKey(), entry.getValue())));
		graph.insertNewVertexes(vertexList.values());
		return vertexList;
	}

	private <V, E> void doInsertEdges(Map<String, Vertex<V>> vertexList, Map<Pair<String, String>, E> edges, Graph<V, E> graph) {
		edges.entrySet().forEach(entry -> {
			doInsertSingleEdge(vertexList.values(), entry.getKey(), entry.getValue(), graph);
		});
	}

	private <V, E> void doInsertSingleEdge(Collection<Vertex<V>> vertexList, Pair<String, String> vertexNames, E edgeValue, Graph<V, E> graph) {
		getVertexesByNames(vertexList, vertexNames).ifPresent(vertexPair -> {
			Vertex<V> leftElement = vertexPair.getLeft();
			Vertex<V> rightElement = vertexPair.getRight();
			graph.insertNewEdge(leftElement, rightElement, new EdgeImpl<V, E>(leftElement, rightElement, edgeValue));
		});
	}

	private <V> Optional<Pair<Vertex<V>, Vertex<V>>> getVertexesByNames(Collection<Vertex<V>> vertexes, Pair<String, String> names) {
		Optional<Vertex<V>> leftVertex = vertexes.stream().filter(vertex -> vertex.getName().equals(names.getLeft())).findFirst();
		Optional<Vertex<V>> rightVertex = vertexes.stream().filter(vertex -> vertex.getName().equals(names.getRight())).findFirst();
		if (leftVertex.isPresent() && rightVertex.isPresent()) {
			return Optional.of(Pair.of(leftVertex.get(), rightVertex.get()));
		}
		return Optional.empty();
	}

	private <V, E> Graph<V, E> doCreateGraph(Map<Pair<V, V>, E> data, Graph<V, E> graph) {
		Map<V, Vertex<V>> vertexList = data.keySet().stream().map(keyPair -> Arrays.asList(keyPair.getLeft(), keyPair.getRight()))
				.flatMap(keyList -> keyList.stream()).distinct()
				.collect(Collectors.toMap(vertexValue -> vertexValue, this::createSigleVertexFromValue));
		data.entrySet().forEach(entry -> {
			Vertex<V> startVertex = vertexList.get(entry.getKey().getLeft());
			Vertex<V> endVertex = vertexList.get(entry.getKey().getRight());
			graph.insertNewVertex(startVertex);
			graph.insertNewVertex(endVertex);
			graph.insertNewEdge(startVertex, endVertex, new EdgeImpl<V, E>(startVertex, endVertex, entry.getValue()));
		});
		return graph;
	}

	private <V> Vertex<V> createSigleVertexFromValue(V vertexValue) {
		return new VertexImpl<V>(generateVertexName(vertexValue), vertexValue);
	}

	private <V> String generateVertexName(V vertexValue) {
		return "Vertex" + Integer.toHexString(vertexValue.hashCode());
	}

	@Override
	public <V, E> Graph<V, E> createUnDirectedGraph(Map<String, V> vertexes, Map<Couple<String>, E> edges) {
		return doCreateUnDirectedGraph(vertexes, edges, new UnDirectedGraph<V, E>());
	}

	@Override
	public <V, E> Graph<V, E> createUnDirectedGraph(Map<Couple<V>, E> data) {
		return doCreateUnDirectedGraph(data, new UnDirectedGraph<V, E>());
	}

	private <V, E> Graph<V, E> doCreateUnDirectedGraph(Map<String, V> vertexes, Map<Couple<String>, E> edges, Graph<V, E> graph) {
		Map<String, Vertex<V>> vertexList = doInsertVertexes(vertexes, graph);
		doInsertUnDirectedEdges(vertexList, edges, graph);
		return graph;
	}

	private <V, E> void doInsertUnDirectedEdges(Map<String, Vertex<V>> vertexList, Map<Couple<String>, E> edges, Graph<V, E> graph) {
		edges.entrySet().forEach(entry -> {
			doInsertSingleEdge(vertexList.values(), entry.getKey(), entry.getValue(), graph);
		});
	}

	private <V, E> void doInsertSingleEdge(Collection<Vertex<V>> vertexList, Couple<String> vertexNames, E edgeValue, Graph<V, E> graph) {
		getVertexesByNames(vertexList, vertexNames).ifPresent(vertexCouple -> {
			Vertex<V> oneElement = vertexCouple.getOne();
			Vertex<V> anotherElement = vertexCouple.getAnother();
			graph.insertNewEdge(oneElement, anotherElement, new EdgeImpl<V, E>(oneElement, anotherElement, edgeValue));
		});
	}

	private <V> Optional<Couple<Vertex<V>>> getVertexesByNames(Collection<Vertex<V>> vertexes, Couple<String> names) {
		return Couple.of(vertexes.stream().filter(vertex -> names.contains(vertex.getName())).collect(Collectors.toSet()));
	}

	private <V, E> Graph<V, E> doCreateUnDirectedGraph(Map<Couple<V>, E> data, Graph<V, E> graph) {
		Map<V, Vertex<V>> vertexList = data.keySet().stream().map(keyCouple -> keyCouple.getTwoElementList()).flatMap(keyList -> keyList.stream())
				.distinct().collect(Collectors.toMap(vertexValue -> vertexValue, this::createSigleVertexFromValue));
		data.entrySet().forEach(entry -> {
			Vertex<V> startVertex = vertexList.get(entry.getKey().getOne());
			Vertex<V> endVertex = vertexList.get(entry.getKey().getAnother());
			graph.insertNewVertex(startVertex);
			graph.insertNewVertex(endVertex);
			graph.insertNewEdge(startVertex, endVertex, new EdgeImpl<V, E>(startVertex, endVertex, entry.getValue()));
		});
		return graph;
	}
}
