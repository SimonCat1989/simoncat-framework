package com.simoncat.framework.serializer.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simoncat.framework.serializer.api.SimoncatSerializer;

@Configuration
public class SerializerConfig {

	@Bean
    public SimoncatSerializer serializer() throws IOException {
		return null;
    }
}
