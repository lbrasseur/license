package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.util.Arrays;

import ar.com.oxen.license.api.License;

/**
 * Bean-based {@link License} implementation.
 * 
 * @param <I>
 *            The license info type
 */
public class SimpleLicense<I extends Serializable> implements License<I> {
	private static final long serialVersionUID = 2905278555167754416L;
	private final I info;
	private final byte[] authorization;

	public SimpleLicense(I info, byte[] authorization) {
		this.info = requireNonNull(info);
		this.authorization = requireNonNull(authorization);
	}

	@Override
	public I getInfo() {
		return this.info;
	}

	@Override
	public byte[] getAuthorization() {
		return this.authorization;
	}

	@Override
	public String toString() {
		return "SimpleLicense [info=" + info + ", authorization="
				+ Arrays.toString(authorization) + "]";
	}
}
