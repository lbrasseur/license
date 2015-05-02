package ar.com.oxen.license.impl;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;

import ar.com.oxen.license.api.KeyStoreProvider;
import ar.com.oxen.license.api.LicenceException;
import ar.com.oxen.license.api.PublicKeyProvider;

/**
 * Public key provider that reads the key from a {@link KeyStore}.
 */
public class KeyStorePublicKeyProvider implements PublicKeyProvider {
	private String alias;
	private KeyStoreProvider keyStoreProvider;

	public KeyStorePublicKeyProvider(String alias,
			KeyStoreProvider keyStoreProvider) {
		super();
		this.alias = alias;
		this.keyStoreProvider = keyStoreProvider;
	}

	@Override
	public PublicKey getPublicKey() {
		try {
			return this.keyStoreProvider.getKeyStore().getCertificate(alias)
					.getPublicKey();
		} catch (KeyStoreException e) {
			throw new LicenceException(e);
		}
	}

}
