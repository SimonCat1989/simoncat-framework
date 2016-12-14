package com.simoncat.framework.graph.elements.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.ImmutableMap;
import com.simoncat.framework.core.tuple.Couple;
import com.simoncat.framework.graph.elements.Edge;
import com.simoncat.framework.graph.elements.Graph;
import com.simoncat.framework.graph.elements.GraphConfig;
import com.simoncat.framework.graph.elements.GraphFactory;
import com.simoncat.framework.graph.elements.Vertex;
import com.simoncat.framework.graph.mock.SampleEdge;
import com.simoncat.framework.graph.mock.SampleVertex;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GraphConfig.class)
public class GraphFactoryImplTest {

	private static final SampleVertex TEST_SAMPLE_VERTEX_1 = new SampleVertex(UUID.randomUUID().toString());
	private static final SampleVertex TEST_SAMPLE_VERTEX_2 = new SampleVertex(UUID.randomUUID().toString());
	private static final SampleVertex TEST_SAMPLE_VERTEX_3 = new SampleVertex(UUID.randomUUID().toString());
	private static final SampleVertex TEST_SAMPLE_VERTEX_4 = new SampleVertex(UUID.randomUUID().toString());

	private static final Vertex<SampleVertex> TEST_VERTEX_1 = new VertexImpl<>(UUID.randomUUID().toString(), TEST_SAMPLE_VERTEX_1);
	private static final Vertex<SampleVertex> TEST_VERTEX_2 = new VertexImpl<>(UUID.randomUUID().toString(), TEST_SAMPLE_VERTEX_2);
	private static final Vertex<SampleVertex> TEST_VERTEX_3 = new VertexImpl<>(UUID.randomUUID().toString(), TEST_SAMPLE_VERTEX_3);
	private static final Vertex<SampleVertex> TEST_VERTEX_4 = new VertexImpl<>(UUID.randomUUID().toString(), TEST_SAMPLE_VERTEX_4);

	private static final SampleEdge TEST_SAMPLE_EDGE_1_2 = new SampleEdge(UUID.randomUUID().toString());
	private static final SampleEdge TEST_SAMPLE_EDGE_1_3 = new SampleEdge(UUID.randomUUID().toString());
	private static final SampleEdge TEST_SAMPLE_EDGE_1_4 = new SampleEdge(UUID.randomUUID().toString());
	private static final SampleEdge TEST_SAMPLE_EDGE_2_4 = new SampleEdge(UUID.randomUUID().toString());
	private static final SampleEdge TEST_SAMPLE_EDGE_3_4 = new SampleEdge(UUID.randomUUID().toString());

	private static final Edge<SampleVertex, SampleEdge> TEST_EDGE_1_2 = new EdgeImpl<>(TEST_VERTEX_1, TEST_VERTEX_2, TEST_SAMPLE_EDGE_1_2);
	private static final Edge<SampleVertex, SampleEdge> TEST_EDGE_1_3 = new EdgeImpl<>(TEST_VERTEX_1, TEST_VERTEX_3, TEST_SAMPLE_EDGE_1_3);
	private static final Edge<SampleVertex, SampleEdge> TEST_EDGE_1_4 = new EdgeImpl<>(TEST_VERTEX_1, TEST_VERTEX_4, TEST_SAMPLE_EDGE_1_4);
	private static final Edge<SampleVertex, SampleEdge> TEST_EDGE_2_4 = new EdgeImpl<>(TEST_VERTEX_2, TEST_VERTEX_4, TEST_SAMPLE_EDGE_2_4);
	private static final Edge<SampleVertex, SampleEdge> TEST_EDGE_3_4 = new EdgeImpl<>(TEST_VERTEX_3, TEST_VERTEX_4, TEST_SAMPLE_EDGE_3_4);

	private static final SampleVertex TEST_NON_EXIST_SAMPLE_VERTEX_1 = new SampleVertex(UUID.randomUUID().toString());
	private static final SampleVertex TEST_NON_EXIST_SAMPLE_VERTEX_2 = new SampleVertex(UUID.randomUUID().toString());
	private static final Vertex<SampleVertex> TEST_NON_EXIST_VERTEX_1 = new VertexImpl<>(UUID.randomUUID().toString(), TEST_NON_EXIST_SAMPLE_VERTEX_1);
	private static final Vertex<SampleVertex> TEST_NON_EXIST_VERTEX_2 = new VertexImpl<>(UUID.randomUUID().toString(), TEST_NON_EXIST_SAMPLE_VERTEX_2);

	@Autowired
	private GraphFactory graphFactory;

	@Test
	public void testCreateEmptyDirectedGraph() {
		Graph<SampleVertex, SampleEdge> emptyDirectedGraph = graphFactory.createEmptyDirectedGraph();
		assertThat("There should be 0 vertex in graph.", emptyDirectedGraph.getSizeOfVertexs(), is(0));
		assertThat("There should be 0 edge in graph.", emptyDirectedGraph.getSizeOfEdges(), is(0));

		initEmptyTestGraph(emptyDirectedGraph);
		verifyForDirectedGraph(emptyDirectedGraph);
	}

	@Test
	public void testCreateEmptyUnDirectedGraph() {
		Graph<SampleVertex, SampleEdge> emptyUnDirectedGraph = graphFactory.createEmptyUnDirectedGraph();
		assertThat("There should be 0 vertex in graph.", emptyUnDirectedGraph.getSizeOfVertexs(), is(0));
		assertThat("There should be 0 edge in graph.", emptyUnDirectedGraph.getSizeOfEdges(), is(0));

		initEmptyTestGraph(emptyUnDirectedGraph);
		verifyForUnDirectedGraph(emptyUnDirectedGraph);
	}

	private void initEmptyTestGraph(Graph<SampleVertex, SampleEdge> graph) {
		graph.insertNewVertex(TEST_VERTEX_1);
		graph.insertNewVertexes(Arrays.asList(TEST_VERTEX_2, TEST_VERTEX_3, TEST_VERTEX_4));
		graph.insertNewEdge(TEST_VERTEX_1, TEST_VERTEX_2, TEST_EDGE_1_2);
		graph.insertNewEdge(TEST_VERTEX_1, TEST_VERTEX_3, TEST_EDGE_1_3);
		graph.insertNewEdge(TEST_VERTEX_1, TEST_VERTEX_4, TEST_EDGE_1_4);
		graph.insertNewEdge(TEST_VERTEX_2, TEST_VERTEX_4, TEST_EDGE_2_4);
		graph.insertNewEdge(TEST_VERTEX_3, TEST_VERTEX_4, TEST_EDGE_3_4);
	}

	@Test
	public void testCreateDirectedGraphFromVertexesAndEdge() {
		Graph<SampleVertex, SampleEdge> directedGraph = graphFactory.createDirectedGraph(initVertexesMap(), initEdgesMapForDirectedGraph());
		verifyForDirectedGraph(directedGraph);
	}

	@Test
	public void testCreateUnDirectedGraphFromVertexesAndEdge() {
		Graph<SampleVertex, SampleEdge> unDirectedGraph = graphFactory.createUnDirectedGraph(initVertexesMap(), initEdgesMapForUnDirectedGraph());
		verifyForUnDirectedGraph(unDirectedGraph);
	}

	private Map<String, SampleVertex> initVertexesMap() {
		return ImmutableMap.<String, SampleVertex> builder().put(TEST_VERTEX_1.getName(), TEST_VERTEX_1.getValue())
				.put(TEST_VERTEX_2.getName(), TEST_VERTEX_2.getValue()).put(TEST_VERTEX_3.getName(), TEST_VERTEX_3.getValue())
				.put(TEST_VERTEX_4.getName(), TEST_VERTEX_4.getValue()).build();
	}

	private Map<Pair<String, String>, SampleEdge> initEdgesMapForDirectedGraph() {
		return ImmutableMap.<Pair<String, String>, SampleEdge> builder()
				.put(Pair.of(TEST_VERTEX_1.getName(), TEST_VERTEX_2.getName()), TEST_SAMPLE_EDGE_1_2)
				.put(Pair.of(TEST_VERTEX_1.getName(), TEST_VERTEX_3.getName()), TEST_SAMPLE_EDGE_1_3)
				.put(Pair.of(TEST_VERTEX_1.getName(), TEST_VERTEX_4.getName()), TEST_SAMPLE_EDGE_1_4)
				.put(Pair.of(TEST_VERTEX_2.getName(), TEST_VERTEX_4.getName()), TEST_SAMPLE_EDGE_2_4)
				.put(Pair.of(TEST_VERTEX_3.getName(), TEST_VERTEX_4.getName()), TEST_SAMPLE_EDGE_3_4).build();
	}

	private Map<Couple<String>, SampleEdge> initEdgesMapForUnDirectedGraph() {
		return ImmutableMap.<Couple<String>, SampleEdge> builder()
				.put(Couple.of(TEST_VERTEX_1.getName(), TEST_VERTEX_2.getName()).get(), TEST_SAMPLE_EDGE_1_2)
				.put(Couple.of(TEST_VERTEX_1.getName(), TEST_VERTEX_3.getName()).get(), TEST_SAMPLE_EDGE_1_3)
				.put(Couple.of(TEST_VERTEX_1.getName(), TEST_VERTEX_4.getName()).get(), TEST_SAMPLE_EDGE_1_4)
				.put(Couple.of(TEST_VERTEX_2.getName(), TEST_VERTEX_4.getName()).get(), TEST_SAMPLE_EDGE_2_4)
				.put(Couple.of(TEST_VERTEX_3.getName(), TEST_VERTEX_4.getName()).get(), TEST_SAMPLE_EDGE_3_4).build();
	}

	@Test
	public void testCreateDirectedGraphFromDataMap() {
		Graph<SampleVertex, SampleEdge> directedGraph = graphFactory.createDirectedGraph(initDataMapForDirectedGraph());

		verifyNormalCaseOfGetSizeOfVertexs(directedGraph);
		verifyNormalCaseOfGetSizeOfEdgesInDirectedGraph(directedGraph);
		verifyNormalCaseOfGetAllRoutesInDirectedGraph(directedGraph);
	}

	@Test
	public void testCreateUnDirectedGraphFromDataMap() {
		Graph<SampleVertex, SampleEdge> unDirectedGraph = graphFactory.createUnDirectedGraph(initDataMapForUnDirectedGraph());

		verifyNormalCaseOfGetSizeOfVertexs(unDirectedGraph);
		verifyNormalCaseOfGetSizeOfEdgesInUnDirectedGraph(unDirectedGraph);
		verifyNormalCaseOfGetAllRoutesInUnDirectedGraph(unDirectedGraph);
	}

	private Map<Pair<SampleVertex, SampleVertex>, SampleEdge> initDataMapForDirectedGraph() {
		return ImmutableMap.<Pair<SampleVertex, SampleVertex>, SampleEdge> builder()
				.put(Pair.of(TEST_VERTEX_1.getValue(), TEST_VERTEX_2.getValue()), TEST_SAMPLE_EDGE_1_2)
				.put(Pair.of(TEST_VERTEX_1.getValue(), TEST_VERTEX_3.getValue()), TEST_SAMPLE_EDGE_1_3)
				.put(Pair.of(TEST_VERTEX_1.getValue(), TEST_VERTEX_4.getValue()), TEST_SAMPLE_EDGE_1_4)
				.put(Pair.of(TEST_VERTEX_2.getValue(), TEST_VERTEX_4.getValue()), TEST_SAMPLE_EDGE_2_4)
				.put(Pair.of(TEST_VERTEX_3.getValue(), TEST_VERTEX_4.getValue()), TEST_SAMPLE_EDGE_3_4).build();
	}

	private Map<Couple<SampleVertex>, SampleEdge> initDataMapForUnDirectedGraph() {
		return ImmutableMap.<Couple<SampleVertex>, SampleEdge> builder()
				.put(Couple.of(TEST_VERTEX_1.getValue(), TEST_VERTEX_2.getValue()).get(), TEST_SAMPLE_EDGE_1_2)
				.put(Couple.of(TEST_VERTEX_1.getValue(), TEST_VERTEX_3.getValue()).get(), TEST_SAMPLE_EDGE_1_3)
				.put(Couple.of(TEST_VERTEX_1.getValue(), TEST_VERTEX_4.getValue()).get(), TEST_SAMPLE_EDGE_1_4)
				.put(Couple.of(TEST_VERTEX_2.getValue(), TEST_VERTEX_4.getValue()).get(), TEST_SAMPLE_EDGE_2_4)
				.put(Couple.of(TEST_VERTEX_3.getValue(), TEST_VERTEX_4.getValue()).get(), TEST_SAMPLE_EDGE_3_4).build();
	}

	private void verifyForDirectedGraph(Graph<SampleVertex, SampleEdge> graph) {
		verifyCommonGetFunctionsInGraph(graph);

		verifyNormalCaseOfGetSizeOfEdgesInDirectedGraph(graph);
		verifyNormalCaseOfGetEdgeInDirectedGraph(graph);
		verifyNormalCaseOfGetAllAdjacentEdgesInDirectedGraph(graph);
		verifyNormalCaseOfGetAllRoutesInDirectedGraph(graph);
	}

	private void verifyForUnDirectedGraph(Graph<SampleVertex, SampleEdge> graph) {
		verifyCommonGetFunctionsInGraph(graph);

		verifyNormalCaseOfGetSizeOfEdgesInUnDirectedGraph(graph);
		verifyNormalCaseOfGetEdgeInUnDirectedGraph(graph);
		verifyNormalCaseOfGetAllAdjacentEdgesInUnDirectedGraph(graph);
		verifyNormalCaseOfGetAllRoutesInUnDirectedGraph(graph);
	}

	private void verifyCommonGetFunctionsInGraph(Graph<SampleVertex, SampleEdge> graph) {
		verifyNormalCaseOfGetSizeOfVertexs(graph);
		verifyNormalCaseOfGetEdge(graph);
		verifyNormalCaseOfGetVertexByVertexName(graph);
		verifyNormalCaseOfGetVertexByVertexValue(graph);

		verifyCornerCaseOfGetEdge(graph);
		verifyCornerCaseOfGetVertexByVertexName(graph);
		verifyCornerCaseOfGetVertexByVertexValue(graph);
		verifyCornerCaseOfGetAllAdjacentEdges(graph);
	}

	private void verifyNormalCaseOfGetSizeOfVertexs(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("There should be 4 vertex in graph.", graph.getSizeOfVertexs(), is(4));
	}

	private void verifyNormalCaseOfGetSizeOfEdgesInDirectedGraph(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("There should be 5 edge in graph.", graph.getSizeOfEdges(), is(5));
	}

	private void verifyNormalCaseOfGetSizeOfEdgesInUnDirectedGraph(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("There should be 5 edge in graph.", graph.getSizeOfEdges(), is(5));
	}

	private void verifyNormalCaseOfGetEdge(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Can Get edge from vertex 1 to 2.", graph.getEdge(TEST_VERTEX_1, TEST_VERTEX_2).get().getValue(), is(TEST_SAMPLE_EDGE_1_2));
		assertThat("Can Get edge from vertex 1 to 3", graph.getEdge(TEST_VERTEX_1, TEST_VERTEX_3).get().getValue(), is(TEST_SAMPLE_EDGE_1_3));
		assertThat("Can Get edge from vertex 1 to 4", graph.getEdge(TEST_VERTEX_1, TEST_VERTEX_4).get().getValue(), is(TEST_SAMPLE_EDGE_1_4));
		assertThat("Can Get edge from vertex 2 to 4", graph.getEdge(TEST_VERTEX_2, TEST_VERTEX_4).get().getValue(), is(TEST_SAMPLE_EDGE_2_4));
		assertThat("Can Get edge from vertex 3 to 4", graph.getEdge(TEST_VERTEX_3, TEST_VERTEX_4).get().getValue(), is(TEST_SAMPLE_EDGE_3_4));

		assertThat("Can NOT Get edge from vertex 1 to 1.", graph.getEdge(TEST_VERTEX_1, TEST_VERTEX_1).isPresent(), is(false));
		assertThat("Can NOT Get edge from vertex 2 to 2.", graph.getEdge(TEST_VERTEX_2, TEST_VERTEX_2).isPresent(), is(false));
		assertThat("Can NOT Get edge from vertex 3 to 3.", graph.getEdge(TEST_VERTEX_3, TEST_VERTEX_3).isPresent(), is(false));
		assertThat("Can NOT Get edge from vertex 4 to 4.", graph.getEdge(TEST_VERTEX_4, TEST_VERTEX_4).isPresent(), is(false));

		assertThat("Can NOT Get edge from vertex 2 to 3.", graph.getEdge(TEST_VERTEX_2, TEST_VERTEX_3).isPresent(), is(false));
		assertThat("Can NOT Get edge from vertex 3 to 2.", graph.getEdge(TEST_VERTEX_3, TEST_VERTEX_2).isPresent(), is(false));
	}

	private void verifyNormalCaseOfGetEdgeInDirectedGraph(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Can NOT Get edge from vertex 2 to 1.", graph.getEdge(TEST_VERTEX_2, TEST_VERTEX_1).isPresent(), is(false));
		assertThat("Can NOT Get edge from vertex 3 to 1.", graph.getEdge(TEST_VERTEX_3, TEST_VERTEX_1).isPresent(), is(false));
		assertThat("Can NOT Get edge from vertex 4 to 1.", graph.getEdge(TEST_VERTEX_4, TEST_VERTEX_1).isPresent(), is(false));
		assertThat("Can NOT Get edge from vertex 4 to 3.", graph.getEdge(TEST_VERTEX_4, TEST_VERTEX_3).isPresent(), is(false));
		assertThat("Can NOT Get edge from vertex 4 to 2.", graph.getEdge(TEST_VERTEX_4, TEST_VERTEX_2).isPresent(), is(false));
	}

	private void verifyNormalCaseOfGetEdgeInUnDirectedGraph(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Can Get edge from vertex 2 to 1.", graph.getEdge(TEST_VERTEX_2, TEST_VERTEX_1).get().getValue(), is(TEST_SAMPLE_EDGE_1_2));
		assertThat("Can Get edge from vertex 3 to 1.", graph.getEdge(TEST_VERTEX_3, TEST_VERTEX_1).get().getValue(), is(TEST_SAMPLE_EDGE_1_3));
		assertThat("Can Get edge from vertex 4 to 1.", graph.getEdge(TEST_VERTEX_4, TEST_VERTEX_1).get().getValue(), is(TEST_SAMPLE_EDGE_1_4));
		assertThat("Can Get edge from vertex 4 to 3.", graph.getEdge(TEST_VERTEX_4, TEST_VERTEX_3).get().getValue(), is(TEST_SAMPLE_EDGE_3_4));
		assertThat("Can Get edge from vertex 4 to 2.", graph.getEdge(TEST_VERTEX_4, TEST_VERTEX_2).get().getValue(), is(TEST_SAMPLE_EDGE_2_4));
	}

	private void verifyNormalCaseOfGetVertexByVertexName(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Can Get Vertex 1 by vertex name", graph.getVertexByName(TEST_VERTEX_1.getName()).get(), is(TEST_VERTEX_1));
		assertThat("Can Get Vertex 2 by vertex name", graph.getVertexByName(TEST_VERTEX_2.getName()).get(), is(TEST_VERTEX_2));
		assertThat("Can Get Vertex 3 by vertex name", graph.getVertexByName(TEST_VERTEX_3.getName()).get(), is(TEST_VERTEX_3));
		assertThat("Can Get Vertex 4 by vertex name", graph.getVertexByName(TEST_VERTEX_4.getName()).get(), is(TEST_VERTEX_4));
	}

	private void verifyNormalCaseOfGetVertexByVertexValue(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Can Get Vertex 1 by vertex value", graph.getVertexByValue(TEST_SAMPLE_VERTEX_1).get(), is(TEST_VERTEX_1));
		assertThat("Can Get Vertex 2 by vertex value", graph.getVertexByValue(TEST_SAMPLE_VERTEX_2).get(), is(TEST_VERTEX_2));
		assertThat("Can Get Vertex 3 by vertex value", graph.getVertexByValue(TEST_SAMPLE_VERTEX_3).get(), is(TEST_VERTEX_3));
		assertThat("Can Get Vertex 4 by vertex value", graph.getVertexByValue(TEST_SAMPLE_VERTEX_4).get(), is(TEST_VERTEX_4));
	}

	private void verifyNormalCaseOfGetAllAdjacentEdgesInDirectedGraph(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Size of adjacent edges of vertex 1 should be 3", graph.getAllAdjacentEdges(TEST_VERTEX_1).size(), is(3));
		assertThat("Size of adjacent edges of vertex 2 should be 1", graph.getAllAdjacentEdges(TEST_VERTEX_2).size(), is(1));
		assertThat("Size of adjacent edges of vertex 3 should be 1", graph.getAllAdjacentEdges(TEST_VERTEX_3).size(), is(1));
		assertThat("Size of adjacent edges of vertex 4 should be 0", graph.getAllAdjacentEdges(TEST_VERTEX_4).size(), is(0));
	}

	private void verifyNormalCaseOfGetAllAdjacentEdgesInUnDirectedGraph(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Size of adjacent edges of vertex 1 should be 3", graph.getAllAdjacentEdges(TEST_VERTEX_1).size(), is(3));
		assertThat("Size of adjacent edges of vertex 2 should be 1", graph.getAllAdjacentEdges(TEST_VERTEX_2).size(), is(2));
		assertThat("Size of adjacent edges of vertex 3 should be 1", graph.getAllAdjacentEdges(TEST_VERTEX_3).size(), is(2));
		assertThat("Size of adjacent edges of vertex 4 should be 0", graph.getAllAdjacentEdges(TEST_VERTEX_4).size(), is(3));
	}

	private void verifyNormalCaseOfGetAllRoutesInDirectedGraph(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Size of all edges should be 5", graph.getAllRoutes().size(), is(5));
	}

	private void verifyNormalCaseOfGetAllRoutesInUnDirectedGraph(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Size of all edges should be 5", graph.getAllRoutes().size(), is(5));
	}

	private void verifyCornerCaseOfGetEdge(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Can NOT Get edge with 1 null vertex.", graph.getEdge(null, TEST_VERTEX_1).isPresent(), is(false));
		assertThat("Can NOT Get edge with 1 null vertex.", graph.getEdge(TEST_VERTEX_1, null).isPresent(), is(false));
		assertThat("Can NOT Get edge with 2 null vertexes.", graph.getEdge(null, null).isPresent(), is(false));

		assertThat("Can NOT Get edge with 2 non-existing vertexes.", graph.getEdge(TEST_NON_EXIST_VERTEX_1, TEST_NON_EXIST_VERTEX_2).isPresent(),
				is(false));
		assertThat("Can NOT Get edge with 1 non-existing vertex.", graph.getEdge(TEST_VERTEX_1, TEST_NON_EXIST_VERTEX_2).isPresent(), is(false));
		assertThat("Can NOT Get edge with 1 non-existing vertex.", graph.getEdge(TEST_NON_EXIST_VERTEX_2, TEST_VERTEX_1).isPresent(), is(false));
	}

	private void verifyCornerCaseOfGetVertexByVertexName(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Can NOT Get vertex with null name.", graph.getVertexByName(null).isPresent(), is(false));
		assertThat("Can NOT Get vertex with empty name.", graph.getVertexByName("").isPresent(), is(false));
		assertThat("Can NOT Get vertex with name contains whitespace only.", graph.getVertexByName(" ").isPresent(), is(false));
		assertThat("Can NOT Get vertex with non-existing name.", graph.getVertexByName(TEST_NON_EXIST_VERTEX_1.getName()).isPresent(), is(false));
	}

	private void verifyCornerCaseOfGetVertexByVertexValue(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Can NOT Get vertex with null value.", graph.getVertexByValue(null).isPresent(), is(false));
		assertThat("Can NOT Get vertex with non-existing value.", graph.getVertexByValue(TEST_NON_EXIST_VERTEX_1.getValue()).isPresent(), is(false));
	}

	private void verifyCornerCaseOfGetAllAdjacentEdges(Graph<SampleVertex, SampleEdge> graph) {
		assertThat("Can NOT Get adjacent edges with null vertex.", graph.getAllAdjacentEdges(null).size(), is(0));
		assertThat("Can NOT Get adjacent edges with non-existing vertex.", graph.getAllAdjacentEdges(TEST_NON_EXIST_VERTEX_1).size(), is(0));
	}
}
