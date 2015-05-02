package ar.com.oxen.license.impl.function;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

/**
 * Byte array to Base 64 converter.
 */
public class BytesToBase64Function implements Function<byte[], String> {
	@Override
	public String apply(byte[] source) {
		requireNonNull(source);
		return new String(Base64Coder.encode(source));
	}
}
