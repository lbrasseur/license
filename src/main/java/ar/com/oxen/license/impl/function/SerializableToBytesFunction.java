package ar.com.oxen.license.impl.function;

import static java.util.Objects.requireNonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ar.com.oxen.license.api.Function;

/**
 * Serializable object to byte array converter.
 */
public class SerializableToBytesFunction<S extends Serializable> implements
		Function<S, byte[]> {
	@Override
	public byte[] apply(S source) {
		try {
			requireNonNull(source);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(source);
			oos.flush();
			byte[] result = bos.toByteArray();
			oos.close();
			bos.close();
			return result;
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
