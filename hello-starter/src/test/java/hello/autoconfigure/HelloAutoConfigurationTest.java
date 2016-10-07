package hello.autoconfigure;

import hello.ConsoleHelloService;
import hello.HelloService;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloAutoConfigurationTest {

	@Rule
	public OutputCapture output = new OutputCapture();

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(HelloAutoConfiguration.class));

	@Test
	public void defaultServiceIsAutoConfigured() {
		this.contextRunner
				.withPropertyValues("hello.prefix=Test", "hello.suffix=**").run((context) -> {
			assertThat(context).hasSingleBean(HelloService.class);
			HelloService bean = context.getBean(HelloService.class);
			bean.sayHello("World");
			assertThat(this.output.toString()).contains("Test World**");
		});
	}

	@Test
	public void defaultServiceBacksOff() {
		this.contextRunner.withUserConfiguration(UserConfiguration.class).run((context) -> {
			assertThat(context).hasSingleBean(HelloService.class);
			HelloService bean = context.getBean(HelloService.class);
			bean.sayHello("Works");
			assertThat(this.output.toString()).contains("Mine Works*");
		});

	}

	@Configuration
	static class UserConfiguration {

		@Bean
		public HelloService myHelloService() {
			return new ConsoleHelloService("Mine", "*");
		}
	}

}