package ar.com.oxen.commons.converter.impl;

import ar.com.oxen.commons.coder.Base64Coder;
import ar.com.oxen.commons.converter.api.Converter;

/**
 * Base 64 to byte array converter.
 */
public class Base64ToBytesConverter implements Converter<String, byte[]> {
	@Override
	public byte[] convert(String source) {
		return Base64Coder.decode(source);
	}
}
