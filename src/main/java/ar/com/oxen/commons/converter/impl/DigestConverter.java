package ar.com.oxen.commons.converter.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ar.com.oxen.commons.converter.api.ConversionException;
import ar.com.oxen.commons.converter.api.Converter;

/**
 * Byte array to byte array converter that applies a message digest algorithm.
 */
public class DigestConverter implements Converter<byte[], byte[]> {
	private String algorithm;

	public DigestConverter(String algorithm) {
		super();
		this.algorithm = algorithm;
	}

	@Override
	public byte[] convert(byte[] source) {
		try {
			MessageDigest messageDigest = MessageDigest
					.getInstance(this.algorithm);
			return messageDigest.digest(source);
		} catch (NoSuchAlgorithmException e) {
			throw new ConversionException(e);
		}
	}
}
