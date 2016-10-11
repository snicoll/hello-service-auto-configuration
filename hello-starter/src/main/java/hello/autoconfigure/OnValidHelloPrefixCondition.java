package hello.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnValidHelloPrefixCondition extends SpringBootCondition {

	private static final String PROPERTY_NAME = "hello.prefix";

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context,
			AnnotatedTypeMetadata metadata) {
		ConditionMessage.Builder condition =
				ConditionMessage.forCondition("ValidHelloPrefix");
		Environment environment = context.getEnvironment();
		if (environment.containsProperty(PROPERTY_NAME)) {
			String value = environment.getProperty(PROPERTY_NAME);
			if (Character.isUpperCase(value.charAt(0))) {
				return ConditionOutcome.match(
						condition.available(String.format("valid prefix ('%s')", value)));
			}
			return ConditionOutcome.noMatch(
					condition.because(String.format("rejected the prefix ‘%s’ as it " +
							"does not start with an upper-case character", value)));

		}
		return ConditionOutcome.noMatch(
				condition.didNotFind("property").items("'hello.prefix'"));
	}

}
