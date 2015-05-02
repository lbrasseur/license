package ar.com.oxen.commons.converter.impl;

import java.security.SecureRandom;

import ar.com.oxen.commons.converter.api.Converter;

/**
 * Byte array to byte array converter that applies a random salt in order to
 * obfuscate the message.
 */
public class SaltConverter implements Converter<byte[], byte[]> {
	@Override
	public byte[] convert(byte[] source) {
		byte salt = new SecureRandom().generateSeed(1)[0];
		byte[] salted = new byte[source.length + 1];
		salted[0] = salt;
		for (int n = 0; n < source.length; n++) {
			salted[n + 1] = (byte) (source[n] ^ salt);
		}
		return salted;
	}
}
