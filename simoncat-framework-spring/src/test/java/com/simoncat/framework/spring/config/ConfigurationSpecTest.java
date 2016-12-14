package com.simoncat.framework.spring.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.simoncat.framework.spring.config.api.SamplePackageImplSameLevelConfig;

public class ConfigurationSpecTest {

	@Test
	public void testConfigInheritanceForPackageImplSubLevel() {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.register(SamplePackageImplSubLevelConfig.class);
			context.refresh();
			assertNotNull(context.getBean(SampleBean.class));
		}
	}
	
	@Test
	public void testConfigInheritanceForPackageImplSameLevel() {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.register(SamplePackageImplSameLevelConfig.class);
			context.refresh();
			assertNotNull(context.getBean(SampleBean.class));
		}
	}
	
	@Test
	public void testConfigInheritanceForSamePackage() {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.register(SampleSamePackageConfig.class);
			context.refresh();
			assertNotNull(context.getBean(SampleBean.class));
		}
	}
	

	@Test
	public void testLoadSameConfigClassMultipleTimes() {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.setAllowCircularReferences(false);
			context.setAllowBeanDefinitionOverriding(false);
			context.register(SamplePackageImplSubLevelConfig.class, SamplePackageImplSubLevelConfig.class);
			context.refresh();
			assertNotNull(context.getBean(SampleBean.class));
		}
	}
}
