package ar.com.oxen.license.impl;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Provider;

import ar.com.oxen.commons.converter.impl.SerializableToBytesConverter;
import ar.com.oxen.license.api.LicenseAuthorizationValidator;
import ar.com.oxen.license.api.PublicKeyProvider;
import ar.com.oxen.license.api.SignatureProvider;

/**
 * JSR330 provider for creating a default {@link LicenseAuthorizationValidator}
 * implementation, using digital signatures and a serializable license.
 * 
 * @param <I>
 *            The license info type
 */
public class DefaultLicenseAuthorizationValidatorProvider<I extends Serializable>
		implements Provider<LicenseAuthorizationValidator<I>> {
	private SignatureProvider signatureProvider;
	private PublicKeyProvider publicKeyProvider;

	@Inject
	public DefaultLicenseAuthorizationValidatorProvider(
			SignatureProvider signatureProvider,
			PublicKeyProvider publicKeyProvider) {
		super();
		this.signatureProvider = signatureProvider;
		this.publicKeyProvider = publicKeyProvider;
	}

	@Override
	public LicenseAuthorizationValidator<I> get() {
		return new SignatureLicenseAuthorizationValidator<I>(
				this.signatureProvider, this.publicKeyProvider,
				new SerializableToBytesConverter<I>());
	}
}
