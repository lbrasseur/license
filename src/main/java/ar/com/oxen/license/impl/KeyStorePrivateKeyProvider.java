package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;

import ar.com.oxen.license.api.KeyStoreProvider;
import ar.com.oxen.license.api.LicenceException;
import ar.com.oxen.license.api.PrivateKeyProvider;

/**
 * Private key provider that reads the key from a {@link KeyStore}.
 */
public class KeyStorePrivateKeyProvider implements PrivateKeyProvider {
	private final String alias;
	private final String password;
	private final KeyStoreProvider keyStoreProvider;

	public KeyStorePrivateKeyProvider(String alias, String password,
			KeyStoreProvider keyStoreProvider) {
		this.alias = requireNonNull(alias);
		this.password = requireNonNull(password);
		this.keyStoreProvider = requireNonNull(keyStoreProvider);
	}

	@Override
	public PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStoreProvider.getKeyStore().getKey(alias,
					password.toCharArray());
		} catch (UnrecoverableKeyException e) {
			throw new LicenceException(e);
		} catch (KeyStoreException e) {
			throw new LicenceException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new LicenceException(e);
		}
	}

}
