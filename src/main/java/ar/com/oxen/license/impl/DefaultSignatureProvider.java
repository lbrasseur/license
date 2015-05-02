package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;

import ar.com.oxen.license.api.LicenceException;
import ar.com.oxen.license.api.SignatureProvider;

/**
 * Signature provider which uses standard Java security classes.
 */
public class DefaultSignatureProvider implements SignatureProvider {
	private final String algorithm;

	public DefaultSignatureProvider(String algorithm) {
		this.algorithm = requireNonNull(algorithm);
	}

	@Override
	public Signature getSignature() {
		try {
			return Signature.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new LicenceException(e);
		}
	}
}
