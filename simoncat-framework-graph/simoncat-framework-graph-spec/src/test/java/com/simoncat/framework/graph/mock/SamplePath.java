package com.simoncat.framework.graph.mock;

import com.simoncat.framework.graph.elements.Edge;
import com.simoncat.framework.graph.elements.PathAttributes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SamplePath implements PathAttributes<SampleVertex, SampleEdge> {

	private int score;

	@Override
	public int compareTo(PathAttributes<SampleVertex, SampleEdge> o) {
		return 0;
	}

	@Override
	public void addNewEdge(Edge<SampleVertex, SampleEdge> edge) {
	}

	@Override
	public PathAttributes<SampleVertex, SampleEdge> makeCopy() {
		return new SamplePath(score);
	}
}
