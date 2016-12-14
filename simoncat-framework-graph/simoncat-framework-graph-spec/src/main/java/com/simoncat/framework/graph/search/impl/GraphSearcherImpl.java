package com.simoncat.framework.graph.search.impl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.simoncat.framework.graph.elements.Edge;
import com.simoncat.framework.graph.elements.Graph;
import com.simoncat.framework.graph.elements.Path;
import com.simoncat.framework.graph.elements.PathAttributes;
import com.simoncat.framework.graph.elements.Vertex;
import com.simoncat.framework.graph.elements.impl.Paths;
import com.simoncat.framework.graph.search.GraphSearcher;

public class GraphSearcherImpl<VERTEX_VAL, EDGE_VAL> implements GraphSearcher<VERTEX_VAL, EDGE_VAL> {

	@Override
	public List<Path<VERTEX_VAL, EDGE_VAL>> depthFirstSearch(Graph<VERTEX_VAL, EDGE_VAL> graph, Vertex<VERTEX_VAL> start,
			Vertex<VERTEX_VAL> destination, int maxHops, PathAttributes<VERTEX_VAL, EDGE_VAL> attributes) {
		Objects.requireNonNull(start, "We can not get path with an empty starter site.");
		Objects.requireNonNull(destination, "We can not get path with an empty destination site.");

		List<Path<VERTEX_VAL, EDGE_VAL>> pathList = Lists.newLinkedList();
		Set<String> visitedSites = Sets.newHashSet();
		Stack<GraphSearchStatusWrapper<VERTEX_VAL, EDGE_VAL>> workingStack = new Stack<>();
		visitedSites.add(start.getName());
		workingStack.push(new GraphSearchStatusWrapper<VERTEX_VAL, EDGE_VAL>(Paths.newPath(attributes), visitedSites, start));

		doDepthFirstSearch(graph, workingStack, destination, maxHops, pathList);

		return pathList;
	}

	private void doDepthFirstSearch(Graph<VERTEX_VAL, EDGE_VAL> graph, Stack<GraphSearchStatusWrapper<VERTEX_VAL, EDGE_VAL>> workingStack,
			Vertex<VERTEX_VAL> destination, int maxHops, List<Path<VERTEX_VAL, EDGE_VAL>> pathList) {
		if (!workingStack.isEmpty()) {
			GraphSearchStatusWrapper<VERTEX_VAL, EDGE_VAL> currentStatus = workingStack.pop();
			if (currentStatus.getVisitedSites().size() <= maxHops) {
				Vertex<VERTEX_VAL> currentVertex = currentStatus.getCurrentVertex();
				Collection<Edge<VERTEX_VAL, EDGE_VAL>> adjacentEdges = graph.getAllAdjacentEdges(currentVertex);
				adjacentEdges.stream().forEach(adjacentEdge -> {
					Vertex<VERTEX_VAL> adjacentVertex = adjacentEdge.getVertexExceptFor(currentVertex);
					if (destination.equals(adjacentVertex)) {
						pathList.add(currentStatus.addNewVisitedEdge(adjacentEdge).getVisitedPath());
					} else if (!currentStatus.isVisited(adjacentVertex)) {
						workingStack.push(GraphSearchStatusWrappers.newStatus(currentStatus, adjacentVertex, adjacentEdge));
					}
				});
			}
			doDepthFirstSearch(graph, workingStack, destination, maxHops, pathList);
		}
	}

	@Override
	public List<Path<VERTEX_VAL, EDGE_VAL>> breadthFirstSearch() {
		// TODO Auto-generated method stub
		return null;
	}

}
