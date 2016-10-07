package hello.autoconfigure;

import hello.HelloService;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloAutoConfigurationTest {

	@Rule
	public OutputCapture output = new OutputCapture();

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(HelloAutoConfiguration.class));

	@Test
	public void defaultServiceIsAutoConfigured() {
		this.contextRunner.run((context) -> {
			assertThat(context).hasSingleBean(HelloService.class);
			HelloService bean = context.getBean(HelloService.class);
			bean.sayHello("World");
			assertThat(this.output.toString()).contains("Hello World!");
		});
	}

}