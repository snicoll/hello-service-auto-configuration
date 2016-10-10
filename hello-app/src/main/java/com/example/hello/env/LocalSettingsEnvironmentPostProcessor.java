package com.example.hello.env;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class LocalSettingsEnvironmentPostProcessor implements EnvironmentPostProcessor {

	private static final String LOCATION = ".hello/settings";

	private static final Logger logger = LoggerFactory
			.getLogger(LocalSettingsEnvironmentPostProcessor.class);

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment,
			SpringApplication springApplication) {
		File file = new File(System.getProperty("user.home"), LOCATION);
		if (file.exists()) {
			MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
			logger.info("Loading local settings from " + file.getAbsolutePath());
			Properties properties = loadProperties(file);
			if (propertySources.contains(
					CommandLinePropertySource.COMMAND_LINE_PROPERTY_SOURCE_NAME)) {
				propertySources.addAfter(
						CommandLinePropertySource.COMMAND_LINE_PROPERTY_SOURCE_NAME,
						new PropertiesPropertySource("hello-local", properties));
			}
			else {
				propertySources
						.addFirst(new PropertiesPropertySource("hello-local", properties));
			}
		}
	}

	private Properties loadProperties(File f) {
		FileSystemResource resource = new FileSystemResource(f);
		try {
			return PropertiesLoaderUtils.loadProperties(resource);
		}
		catch (IOException ex) {
			throw new IllegalStateException("Failed to load local settings from " + f.getAbsolutePath(), ex);
		}
	}

}