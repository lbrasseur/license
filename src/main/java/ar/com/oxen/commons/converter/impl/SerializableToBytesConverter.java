package ar.com.oxen.commons.converter.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ar.com.oxen.commons.converter.api.ConversionException;
import ar.com.oxen.commons.converter.api.Converter;

/**
 * Serializable object to byte array converter.
 */
public class SerializableToBytesConverter<S extends Serializable> implements
		Converter<S, byte[]> {
	@Override
	public byte[] convert(S source) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(source);
			oos.flush();
			byte[] result = bos.toByteArray();
			oos.close();
			bos.close();
			return result;
		} catch (IOException e) {
			throw new ConversionException(e);
		}
	}
}
