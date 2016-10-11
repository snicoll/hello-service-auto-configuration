package hello.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("hello")
public class HelloProperties {

	/**
	 * Prefix of the welcome message. A space is added before the prefix and
	 * the actual message.
	 */
	private String prefix;

	/**
	 * Suffix of the welcome message.
	 */
	private String suffix = "!";

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
