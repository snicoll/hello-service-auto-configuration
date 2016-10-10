package hello;

public class ConsoleHelloService implements HelloService {

	private final String prefix;

	private final String suffix;

	public ConsoleHelloService(String prefix, String suffix) {
		this.prefix = (prefix != null ? prefix : "Hello");
		this.suffix = (suffix != null ? suffix : "!");
		if (!Character.isUpperCase(this.prefix.charAt(0))) {
			throw new InvalidHelloPrefixException(this.prefix);
		}
	}

	public ConsoleHelloService() {
		this(null, null);
	}

	@Override
	public void sayHello(String name) {
		String msg = String.format("%s %s%s", this.prefix, name, this.suffix);
		System.out.println(msg);
	}

}
