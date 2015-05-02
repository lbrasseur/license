package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

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
	private final String alias;
	private final KeyStoreProvider keyStoreProvider;

	public KeyStorePublicKeyProvider(String alias,
			KeyStoreProvider keyStoreProvider) {
		this.alias = requireNonNull(alias);
		this.keyStoreProvider = requireNonNull(keyStoreProvider);
	}

	@Override
	public PublicKey getPublicKey() {
		try {
			return keyStoreProvider.getKeyStore().getCertificate(alias)
					.getPublicKey();
		} catch (KeyStoreException e) {
			throw new LicenceException(e);
		}
	}
}
