package hello.autoconfigure;

import hello.ConsoleHelloService;
import hello.HelloService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(HelloService.class)
public class HelloAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public HelloService helloService() {
		return new ConsoleHelloService();
	}

}
