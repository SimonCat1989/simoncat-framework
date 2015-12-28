package com.simoncat.framework.folder.api;

import java.util.Collection;

public interface FileManager {

	Collection<String> getAll(String path, int level);
	
	Collection<String> getAll(String path);
}
