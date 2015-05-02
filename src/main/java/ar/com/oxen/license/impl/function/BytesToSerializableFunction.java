package ar.com.oxen.license.impl.function;

import static java.util.Objects.requireNonNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.function.Function;

/**
 * Byte array to Serializable object converter.
 */
public class BytesToSerializableFunction<T extends Serializable> implements
		Function<byte[], T> {

	@Override
	public T apply(byte[] source) {
		try {
			requireNonNull(source);
			ByteArrayInputStream bis = new ByteArrayInputStream(source);
			ObjectInputStream ois = new ObjectInputStream(bis);
			@SuppressWarnings("unchecked")
			T result = (T) ois.readObject();
			ois.close();
			bis.close();
			return result;
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
