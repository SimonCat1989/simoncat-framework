package com.simoncat.framework.spring.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@Conditional({ ConfigurationBeanLoadingCondition.class })
@Import(ConfigurationSpec.ImportSelectorImpl.class)
public @interface ConfigurationSpec {

	Class<?> value();

	ConfigurationImplementationClassLocation location() default ConfigurationImplementationClassLocation.SAME_PACKAGE;

	ConfigurationImplementationClassSuffix suffix() default ConfigurationImplementationClassSuffix.SUFFIX_IMPL;

	static final class ImportSelectorImpl implements ImportSelector {
		@Override
		public String[] selectImports(AnnotationMetadata importingClassMetadata) {
			Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(ConfigurationSpec.class.getTypeName());
			Class<?> specClass = (Class<?>) attributes.get("value");
			ConfigurationImplementationClassLocation location = (ConfigurationImplementationClassLocation) attributes.get("location");
			ConfigurationImplementationClassSuffix suffix = (ConfigurationImplementationClassSuffix) attributes.get("suffix");

			String implClassName = location.getLocation(specClass) + suffix.getSuffix();
			if (isExist(implClassName)) {
				return new String[] { implClassName };
			}
			return new String[] {};
		}

		private boolean isExist(String className) {
			try {
				Class.forName(className);
				return true;
			} catch (ClassNotFoundException e) {
				return false;
			}
		}
	}
}
