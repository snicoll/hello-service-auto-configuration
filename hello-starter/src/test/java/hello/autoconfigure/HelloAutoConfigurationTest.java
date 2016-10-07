package hello.autoconfigure;

import org.junit.Rule;
import org.junit.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.rule.OutputCapture;

public class HelloAutoConfigurationTest {

	@Rule
	public OutputCapture output = new OutputCapture();

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(HelloAutoConfiguration.class));

	@Test
	public void defaultServiceIsAutoConfigured() {

	}

}