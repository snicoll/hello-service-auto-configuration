package hello.autoconfigure;

import hello.ConsoleHelloService;
import hello.HelloService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(HelloService.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

	private final HelloProperties properties;

	public HelloAutoConfiguration(HelloProperties properties) {
		this.properties = properties;
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnValidHelloPrefix
	public HelloService helloService() {
		return new ConsoleHelloService(this.properties.getPrefix(),
				this.properties.getSuffix());
	}

}
