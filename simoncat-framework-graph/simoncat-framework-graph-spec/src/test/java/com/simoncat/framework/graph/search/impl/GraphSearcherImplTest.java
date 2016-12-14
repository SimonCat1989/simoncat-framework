package com.simoncat.framework.graph.search.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.ImmutableMap;
import com.simoncat.framework.core.tuple.Couple;
import com.simoncat.framework.graph.elements.Graph;
import com.simoncat.framework.graph.elements.GraphConfig;
import com.simoncat.framework.graph.elements.GraphFactory;
import com.simoncat.framework.graph.elements.Path;
import com.simoncat.framework.graph.mock.SampleEdge;
import com.simoncat.framework.graph.mock.SamplePath;
import com.simoncat.framework.graph.mock.SampleVertex;
import com.simoncat.framework.graph.search.GraphSearcher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { GraphSearcherConfig.class, GraphConfig.class })
public class GraphSearcherImplTest {

	@Autowired
	private GraphSearcher<SampleVertex, SampleEdge> graphSearcher;

	@Autowired
	private GraphFactory graphFactory;

	private static final SampleVertex TEST_SAMPLE_VERTEX_1 = new SampleVertex(UUID.randomUUID().toString());
	private static final SampleVertex TEST_SAMPLE_VERTEX_2 = new SampleVertex(UUID.randomUUID().toString());
	private static final SampleVertex TEST_SAMPLE_VERTEX_3 = new SampleVertex(UUID.randomUUID().toString());
	private static final SampleVertex TEST_SAMPLE_VERTEX_4 = new SampleVertex(UUID.randomUUID().toString());

	private static final SampleEdge TEST_SAMPLE_EDGE_1_2 = new SampleEdge(UUID.randomUUID().toString());
	private static final SampleEdge TEST_SAMPLE_EDGE_1_3 = new SampleEdge(UUID.randomUUID().toString());
	private static final SampleEdge TEST_SAMPLE_EDGE_1_4 = new SampleEdge(UUID.randomUUID().toString());
	private static final SampleEdge TEST_SAMPLE_EDGE_2_4 = new SampleEdge(UUID.randomUUID().toString());
	private static final SampleEdge TEST_SAMPLE_EDGE_3_4 = new SampleEdge(UUID.randomUUID().toString());

	private Graph<SampleVertex, SampleEdge> directedGraph;
	private Graph<SampleVertex, SampleEdge> unDirectedGraph;

	@Before
	public void setup() {
		directedGraph = graphFactory.createDirectedGraph(initVertexesMap(), initEdgesMapForDirectedGraph());
		unDirectedGraph = graphFactory.createUnDirectedGraph(initVertexesMap(), initEdgesMapForUnDirectedGraph());
	}

	@After
	public void tearDown() {
		directedGraph = null;
		unDirectedGraph = null;
	}

	private Map<String, SampleVertex> initVertexesMap() {
		return ImmutableMap.<String, SampleVertex> builder().put("TEST_SAMPLE_VERTEX_1", TEST_SAMPLE_VERTEX_1)
				.put("TEST_SAMPLE_VERTEX_2", TEST_SAMPLE_VERTEX_2).put("TEST_SAMPLE_VERTEX_3", TEST_SAMPLE_VERTEX_3)
				.put("TEST_SAMPLE_VERTEX_4", TEST_SAMPLE_VERTEX_4).build();
	}

	private Map<Pair<String, String>, SampleEdge> initEdgesMapForDirectedGraph() {
		return ImmutableMap.<Pair<String, String>, SampleEdge> builder()
				.put(Pair.of("TEST_SAMPLE_VERTEX_1", "TEST_SAMPLE_VERTEX_2"), TEST_SAMPLE_EDGE_1_2)
				.put(Pair.of("TEST_SAMPLE_VERTEX_1", "TEST_SAMPLE_VERTEX_3"), TEST_SAMPLE_EDGE_1_3)
				.put(Pair.of("TEST_SAMPLE_VERTEX_1", "TEST_SAMPLE_VERTEX_4"), TEST_SAMPLE_EDGE_1_4)
				.put(Pair.of("TEST_SAMPLE_VERTEX_2", "TEST_SAMPLE_VERTEX_4"), TEST_SAMPLE_EDGE_2_4)
				.put(Pair.of("TEST_SAMPLE_VERTEX_3", "TEST_SAMPLE_VERTEX_4"), TEST_SAMPLE_EDGE_3_4).build();
	}

	private Map<Couple<String>, SampleEdge> initEdgesMapForUnDirectedGraph() {
		return ImmutableMap.<Couple<String>, SampleEdge> builder()
				.put(Couple.of("TEST_SAMPLE_VERTEX_1", "TEST_SAMPLE_VERTEX_2").get(), TEST_SAMPLE_EDGE_1_2)
				.put(Couple.of("TEST_SAMPLE_VERTEX_1", "TEST_SAMPLE_VERTEX_3").get(), TEST_SAMPLE_EDGE_1_3)
				.put(Couple.of("TEST_SAMPLE_VERTEX_1", "TEST_SAMPLE_VERTEX_4").get(), TEST_SAMPLE_EDGE_1_4)
				.put(Couple.of("TEST_SAMPLE_VERTEX_2", "TEST_SAMPLE_VERTEX_4").get(), TEST_SAMPLE_EDGE_2_4)
				.put(Couple.of("TEST_SAMPLE_VERTEX_3", "TEST_SAMPLE_VERTEX_4").get(), TEST_SAMPLE_EDGE_3_4).build();
	}

	@Test
	public void testDepthFirstSearchInDirectedGraph() {
		List<Path<SampleVertex, SampleEdge>> results_1_4 = graphSearcher.depthFirstSearch(directedGraph,
				directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_1").get(), directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_4").get(), 2,
				new SamplePath(0));
		assertThat("There should be 3 paths.", results_1_4.size(), is(3));

		List<Path<SampleVertex, SampleEdge>> results_1_3 = graphSearcher.depthFirstSearch(directedGraph,
				directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_1").get(), directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_3").get(), 2,
				new SamplePath(0));
		assertThat("There should be 3 paths.", results_1_3.size(), is(1));

		List<Path<SampleVertex, SampleEdge>> results_1_2 = graphSearcher.depthFirstSearch(directedGraph,
				directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_1").get(), directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_2").get(), 2,
				new SamplePath(0));
		assertThat("There should be 3 paths.", results_1_2.size(), is(1));

		List<Path<SampleVertex, SampleEdge>> results_2_4 = graphSearcher.depthFirstSearch(directedGraph,
				directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_2").get(), directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_4").get(), 2,
				new SamplePath(0));
		assertThat("There should be 3 paths.", results_2_4.size(), is(1));
	}

	@Test
	public void testDepthFirstSearchInUnDirectedGraph() {
		List<Path<SampleVertex, SampleEdge>> results_1_4 = graphSearcher.depthFirstSearch(unDirectedGraph,
				directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_1").get(), directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_4").get(), 2,
				new SamplePath(0));
		assertThat("There should be 3 paths.", results_1_4.size(), is(3));

		List<Path<SampleVertex, SampleEdge>> results_1_3 = graphSearcher.depthFirstSearch(unDirectedGraph,
				directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_1").get(), directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_3").get(), 2,
				new SamplePath(0));
		assertThat("There should be 3 paths.", results_1_3.size(), is(2));

		List<Path<SampleVertex, SampleEdge>> results_1_2 = graphSearcher.depthFirstSearch(unDirectedGraph,
				directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_1").get(), directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_2").get(), 2,
				new SamplePath(0));
		assertThat("There should be 3 paths.", results_1_2.size(), is(2));

		List<Path<SampleVertex, SampleEdge>> results_2_4 = graphSearcher.depthFirstSearch(unDirectedGraph,
				directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_2").get(), directedGraph.getVertexByName("TEST_SAMPLE_VERTEX_4").get(), 2,
				new SamplePath(0));
		assertThat("There should be 3 paths.", results_2_4.size(), is(2));
	}
}
