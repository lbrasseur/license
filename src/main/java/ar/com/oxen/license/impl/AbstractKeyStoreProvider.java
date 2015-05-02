package ar.com.oxen.license.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import ar.com.oxen.license.api.KeyStoreProvider;
import ar.com.oxen.license.api.LicenceException;

public abstract class AbstractKeyStoreProvider implements KeyStoreProvider {
	private String keyStorePassword;

	public AbstractKeyStoreProvider(String keyStorePassword) {
		super();
		this.keyStorePassword = keyStorePassword;
	}

	@Override
	public KeyStore getKeyStore() {
		try {
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			InputStream keyStoreInputStream = this.openKeyStoreInputStream();

			ks.load(keyStoreInputStream, this.keyStorePassword.toCharArray());

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
