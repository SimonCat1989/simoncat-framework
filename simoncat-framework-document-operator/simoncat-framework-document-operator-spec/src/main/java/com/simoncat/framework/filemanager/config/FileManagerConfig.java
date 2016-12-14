package com.simoncat.framework.filemanager.config;

import com.simoncat.framework.filemanager.api.FileManager;
import com.simoncat.framework.spring.config.ConfigurationSpec;

@ConfigurationSpec(FileManagerConfig.class)
public interface FileManagerConfig {

	public FileManager fileManager();
}
