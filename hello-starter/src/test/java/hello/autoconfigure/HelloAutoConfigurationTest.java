package hello.autoconfigure;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class HelloAutoConfigurationTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Rule
	public OutputCapture output = new OutputCapture();

	private ConfigurableApplicationContext context;

	@After
	public void closeContext() {
		if (this.context != null) {
			this.context.close();
		}
	}

	@Test
	public void defaultServiceIsAutoConfigured() {

	}

	private void load(Class<?> config, String... environment) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext();
		ctx.register(config);
		EnvironmentTestUtils.addEnvironment(ctx, environment);
		ctx.refresh();
		this.context = ctx;
	}

	@Configuration
	@ImportAutoConfiguration(HelloAutoConfiguration.class)
	static class EmptyConfiguration {

	}

	@Configuration
	@Import(EmptyConfiguration.class)
	static class UserConfiguration {


	}

}