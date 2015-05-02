package ar.com.oxen.license.impl;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Provider;

import ar.com.oxen.commons.converter.impl.SerializableToBytesConverter;
import ar.com.oxen.license.api.LicenseAuthorizer;
import ar.com.oxen.license.api.PrivateKeyProvider;
import ar.com.oxen.license.api.SignatureProvider;

/**
 * JSR330 provider for creating a default {@link LicenseAuthorizer}
 * implementation, using digital signatures.
 * 
 * @param <I>
 *            The license info type
 */
public class DefaultLicenseAuthorizerProvider<I extends Serializable>
		implements Provider<LicenseAuthorizer<I>> {
	private SignatureProvider signatureProvider;
	private PrivateKeyProvider privateKeyProvider;

	@Inject
	public DefaultLicenseAuthorizerProvider(
			SignatureProvider signatureProvider,
			PrivateKeyProvider privateKeyProvider) {
		super();
		this.signatureProvider = signatureProvider;
		this.privateKeyProvider = privateKeyProvider;
	}

	@Override
	public LicenseAuthorizer<I> get() {
		return new SignatureLicenseAuthorizer<I>(this.signatureProvider,
				this.privateKeyProvider, new SerializableToBytesConverter<I>());
	}

}
