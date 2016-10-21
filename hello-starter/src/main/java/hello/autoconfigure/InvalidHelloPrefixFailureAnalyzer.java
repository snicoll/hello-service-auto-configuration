package hello.autoconfigure;

import hello.InvalidHelloPrefixException;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class InvalidHelloPrefixFailureAnalyzer
		extends AbstractFailureAnalyzer<InvalidHelloPrefixException> {

	@Override
	protected FailureAnalysis analyze(Throwable rootFailure,
			InvalidHelloPrefixException cause) {
		return new FailureAnalysis(
				String.format("The hello service could not be auto-configured " +
						"properly: '%s' is an invalid prefix", cause.getPrefix()),
				"A valid prefix must start with an upper-case character", cause);
	}
}
