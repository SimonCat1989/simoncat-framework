package com.simoncat.framework.filemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simoncat.framework.filemanager.api.FileManager;
import com.simoncat.framework.filemanager.core.FileManagerImpl;

@Configuration
public class FileManagerConfigImpl implements FileManagerConfig {

	@Bean
	public FileManager fileManager() {
		return new FileManagerImpl();
	}
}
