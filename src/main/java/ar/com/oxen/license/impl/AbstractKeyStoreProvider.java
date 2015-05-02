package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import ar.com.oxen.license.api.KeyStoreProvider;
import ar.com.oxen.license.api.LicenceException;

public abstract class AbstractKeyStoreProvider implements KeyStoreProvider {
	private final String keyStorePassword;

	public AbstractKeyStoreProvider(String keyStorePassword) {
		this.keyStorePassword = requireNonNull(keyStorePassword);
	}

	@Override
	public KeyStore getKeyStore() {
		try {
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			InputStream keyStoreInputStream = openKeyStoreInputStream();

			ks.load(keyStoreInputStream, keyStorePassword.toCharArray());

			keyStoreInputStream.close();

			return ks;
		} catch (KeyStoreException e) {
			throw new LicenceException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new LicenceException(e);
		} catch (CertificateException e) {
			throw new LicenceException(e);
		} catch (IOException e) {
			throw new LicenceException(e);
		}
	}

	protected abstract InputStream openKeyStoreInputStream();

}
