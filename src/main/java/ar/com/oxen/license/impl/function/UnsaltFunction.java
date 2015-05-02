package ar.com.oxen.license.impl.function;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

/**
 * Byte array to byte array converter that removes a salt form an obfuscated
 * message.
 */
public class UnsaltFunction implements Function<byte[], byte[]> {
	@Override
	public byte[] apply(byte[] source) {
		requireNonNull(source);
		byte salt = source[0];
		byte[] unsalted = new byte[source.length - 1];
		for (int n = 0; n < unsalted.length; n++) {
			unsalted[n] = (byte) (source[n + 1] ^ salt);
		}
		return unsalted;
	}
}
