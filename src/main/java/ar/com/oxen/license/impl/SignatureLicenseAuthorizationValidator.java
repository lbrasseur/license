package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;

import ar.com.oxen.license.api.Function;
import ar.com.oxen.license.api.LicenceException;
import ar.com.oxen.license.api.License;
import ar.com.oxen.license.api.LicenseAuthorizationValidator;
import ar.com.oxen.license.api.PublicKeyProvider;
import ar.com.oxen.license.api.SignatureProvider;

/**
 * {@link LicenseAuthorizationValidator} based on digital signatures and
 * {@link Function}.
 * 
 * @param <I>
 *            The license info type
 */
public class SignatureLicenseAuthorizationValidator<I extends Serializable>
		implements LicenseAuthorizationValidator<I> {
	private final SignatureProvider signatureProvider;
	private final PublicKeyProvider publicKeyProvider;
	private final Function<I, byte[]> converter;

	public SignatureLicenseAuthorizationValidator(
			SignatureProvider signatureProvider,
			PublicKeyProvider publicKeyProvider, Function<I, byte[]> converter) {
		this.signatureProvider = requireNonNull(signatureProvider);
		this.publicKeyProvider = requireNonNull(publicKeyProvider);
		this.converter = requireNonNull(converter);
	}

	@Override
	public boolean validate(License<I> license) {
		try {
			requireNonNull(license);
			Signature signature = this.signatureProvider.getSignature();
			signature.initVerify(this.publicKeyProvider.getPublicKey());
			signature.update(this.converter.apply(license.getInfo()));
			return signature.verify(license.getAuthorization());
		} catch (SignatureException e) {
			throw new LicenceException(e);
		} catch (InvalidKeyException e) {
			throw new LicenceException(e);
		}
	}
}
