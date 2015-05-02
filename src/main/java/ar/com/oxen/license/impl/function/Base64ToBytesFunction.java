package ar.com.oxen.license.impl.function;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

/**
 * Base 64 to byte array converter.
 */
public class Base64ToBytesFunction implements Function<String, byte[]> {
	@Override
	public byte[] apply(String source) {
		requireNonNull(source);
		return Base64Coder.decode(source);
	}
}
