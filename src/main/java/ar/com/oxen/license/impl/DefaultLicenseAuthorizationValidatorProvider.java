package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Provider;

import ar.com.oxen.license.api.LicenseAuthorizationValidator;
import ar.com.oxen.license.api.PublicKeyProvider;
import ar.com.oxen.license.api.SignatureProvider;
import ar.com.oxen.license.impl.function.SerializableToBytesFunction;

/**
 * JSR330 provider for creating a default {@link LicenseAuthorizationValidator}
 * implementation, using digital signatures and a serializable license.
 * 
 * @param <I>
 *            The license info type
 */
public class DefaultLicenseAuthorizationValidatorProvider<I extends Serializable>
		implements Provider<LicenseAuthorizationValidator<I>> {
	private final SignatureProvider signatureProvider;
	private final PublicKeyProvider publicKeyProvider;

	@Inject
	public DefaultLicenseAuthorizationValidatorProvider(
			SignatureProvider signatureProvider,
			PublicKeyProvider publicKeyProvider) {
		this.signatureProvider = requireNonNull(signatureProvider);
		this.publicKeyProvider = requireNonNull(publicKeyProvider);
	}

	@Override
	public LicenseAuthorizationValidator<I> get() {
		return new SignatureLicenseAuthorizationValidator<I>(signatureProvider,
				publicKeyProvider, new SerializableToBytesFunction<I>());
	}
}
