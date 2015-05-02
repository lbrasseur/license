package ar.com.oxen.license.impl;

import java.io.InputStream;

/**
 * KeyStore provider that reads KeyStore from the class loader.
 */
public class ClassLoaderKeyStoreProvider extends AbstractKeyStoreProvider {
	private String keyStorePath;
	private Class<?> loaderClass;

	public ClassLoaderKeyStoreProvider(String keyStorePath,
			Class<?> loaderClass, String keyStorePassword) {
		super(keyStorePassword);
		this.keyStorePath = keyStorePath;
		this.loaderClass = loaderClass;
	}

	@Override
	protected InputStream openKeyStoreInputStream() {
		return this.loaderClass.getResourceAsStream(this.keyStorePath);
	}
}
