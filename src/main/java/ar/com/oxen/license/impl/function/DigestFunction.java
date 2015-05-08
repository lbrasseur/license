package ar.com.oxen.license.impl.function;

import static java.util.Objects.requireNonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ar.com.oxen.license.api.Function;

/**
 * Byte array to byte array converter that applies a message digest algorithm.
 */
public class DigestFunction implements Function<byte[], byte[]> {
	private String algorithm;

	public DigestFunction(String algorithm) {
		super();
		this.algorithm = algorithm;
	}

	@Override
	public byte[] apply(byte[] source) {
		try {
			requireNonNull(source);
			MessageDigest messageDigest = MessageDigest
					.getInstance(this.algorithm);
			return messageDigest.digest(source);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
