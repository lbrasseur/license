package ar.com.oxen.commons.converter.impl;

import ar.com.oxen.commons.converter.api.Converter;

/**
 * Byte array to byte array converter that removes a salt form an obfuscated
 * message.
 */
public class UnsaltConverter implements Converter<byte[], byte[]> {
	@Override
	public byte[] convert(byte[] source) {
		byte salt = source[0];
		byte[] unsalted = new byte[source.length - 1];
		for (int n = 0; n < unsalted.length; n++) {
			unsalted[n] = (byte) (source[n + 1] ^ salt);
		}
		return unsalted;
	}
}
