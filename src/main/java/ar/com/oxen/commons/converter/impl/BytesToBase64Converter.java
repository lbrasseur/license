package ar.com.oxen.commons.converter.impl;

import ar.com.oxen.commons.coder.Base64Coder;
import ar.com.oxen.commons.converter.api.Converter;

/**
 * Byte array to Base 64 converter.
 */
public class BytesToBase64Converter implements Converter<byte[], String> {
	@Override
	public String convert(byte[] source) {
		return new String(Base64Coder.encode(source));
	}
}
