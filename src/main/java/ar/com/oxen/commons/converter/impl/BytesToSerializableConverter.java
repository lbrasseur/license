package ar.com.oxen.commons.converter.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import ar.com.oxen.commons.converter.api.ConversionException;
import ar.com.oxen.commons.converter.api.Converter;

/**
 * Byte array to Serializable object converter.
 */
public class BytesToSerializableConverter<T extends Serializable> implements
		Converter<byte[], T> {

	@Override
	public T convert(byte[] source) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(source);
			ObjectInputStream ois = new ObjectInputStream(bis);
			@SuppressWarnings("unchecked")
			T result = (T) ois.readObject();
			ois.close();
			bis.close();
			return result;
		} catch (IOException e) {
			throw new ConversionException(e);
		} catch (ClassNotFoundException e) {
			throw new ConversionException(e);
		}
	}
}
