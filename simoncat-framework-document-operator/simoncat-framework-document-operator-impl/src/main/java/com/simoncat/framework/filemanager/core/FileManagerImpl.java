package com.simoncat.framework.filemanager.core;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.simoncat.framework.filemanager.api.FileManager;

public class FileManagerImpl implements FileManager {

	private static final int DEFAULT_LEVEL = 3;

	@Override
	public Collection<String> getAll(String path) {
		return getAll(path, DEFAULT_LEVEL);
	}

	@Override
	public Collection<String> getAll(String path, int level) {
		return (StringUtils.isNotEmpty(path)) ? getDeeperFile(new File(path), level, 1) : Collections.emptyList();
	}

	private Collection<String> getDeeperFile(File folder, int level, int currentLevel) {
		return (currentLevel <= level) ? Arrays.asList(folder.listFiles()).parallelStream()
				.map(file -> (file.isDirectory()) ? getDeeperFile(file, level, currentLevel + 1) : Collections.singletonList(file.getName()))
				.flatMap(list -> list.parallelStream()).filter(StringUtils::isNotEmpty).collect(Collectors.toList()) : Collections.emptyList();
	}
}
