package com.simoncat.framework.spring.config;

import java.util.Map;
import java.util.Objects;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConfigurationBeanLoadingCondition implements ConfigurationCondition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		final ResourceLoader resourceLoader = context.getResourceLoader();
		if (resourceLoader instanceof AbstractApplicationContext && ((AbstractApplicationContext) resourceLoader).isActive()) {
			return true;
		}

		final Map<String, Object> configurationSpecAnnotations = metadata.getAnnotationAttributes(ConfigurationSpec.class.getName());
		return (Objects.nonNull(configurationSpecAnnotations))
				&& (context.getBeanFactory().getBeanNamesForType(((Class<?>) configurationSpecAnnotations.get("value"))).length == 0);
	}

	@Override
	public ConfigurationPhase getConfigurationPhase() {
		return ConfigurationPhase.PARSE_CONFIGURATION;
	}

}
