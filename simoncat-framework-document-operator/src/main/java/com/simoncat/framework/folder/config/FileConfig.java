package com.simoncat.framework.folder.config;

import org.springframework.context.annotation.Bean;

import com.simoncat.framework.folder.api.FileManager;
import com.simoncat.framework.folder.core.FileManagerImpl;

public class FileConfig {

	@Bean
    public FileManager fileManager() {
        return new FileManagerImpl();
    }
}
