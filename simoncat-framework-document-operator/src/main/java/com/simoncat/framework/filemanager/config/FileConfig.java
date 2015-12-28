package com.simoncat.framework.filemanager.config;

import org.springframework.context.annotation.Bean;

import com.simoncat.framework.filemanager.api.FileManager;
import com.simoncat.framework.filemanager.core.FileManagerImpl;

public class FileConfig {

	@Bean
    public FileManager fileManager() {
        return new FileManagerImpl();
    }
}
