package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

import java.io.InputStream;

/**
 * KeyStore provider that reads KeyStore from the class loader.
 */
public class ClassLoaderKeyStoreProvider extends AbstractKeyStoreProvider {
	private final String keyStorePath;
	private final Class<?> loaderClass;

	public ClassLoaderKeyStoreProvider(String keyStorePath,
			Class<?> loaderClass, String keyStorePassword) {
		super(keyStorePassword);
		this.keyStorePath = requireNonNull(keyStorePath);
		this.loaderClass = requireNonNull(loaderClass);
	}

	@Override
	protected InputStream openKeyStoreInputStream() {
		return loaderClass.getResourceAsStream(keyStorePath);
	}
}
