package com.example.hello.env;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;

public class LocalSettingsEnvironmentPostProcessor implements EnvironmentPostProcessor {

	private static final String LOCATION = ".hello/settings";

	private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment,
			SpringApplication springApplication) {
		File file = new File(System.getProperty("user.home"), LOCATION);
		if (file.exists()) {
			MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
			PropertySource<?> propertySource = loadPropertySource(file);
			if (propertySources.contains(
					CommandLinePropertySource.COMMAND_LINE_PROPERTY_SOURCE_NAME)) {
				propertySources.addAfter(
						CommandLinePropertySource.COMMAND_LINE_PROPERTY_SOURCE_NAME,
						propertySource);
			}
			else {
				propertySources.addFirst(propertySource);
			}
		}
	}

	private PropertySource<?> loadPropertySource(File f) {
		FileSystemResource resource = new FileSystemResource(f);
		try {
			return loader.load("hello-local", resource, null);
		}
		catch (IOException ex) {
			throw new IllegalStateException("Failed to load local settings from " + f.getAbsolutePath(), ex);
		}
	}

}