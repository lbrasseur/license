package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.function.Function;

import ar.com.oxen.license.api.LicenceException;
import ar.com.oxen.license.api.License;
import ar.com.oxen.license.api.LicenseAuthorizer;
import ar.com.oxen.license.api.PrivateKeyProvider;
import ar.com.oxen.license.api.SignatureProvider;

/**
 * {@link LicenseAuthorizer} based on digital signatures and {@link Function}.
 * 
 * @param <I>
 *            The license info type
 */
public class SignatureLicenseAuthorizer<I extends Serializable> implements
		LicenseAuthorizer<I> {
	private final SignatureProvider signatureProvider;
	private final PrivateKeyProvider privateKeyProvider;
	private final Function<I, byte[]> converter;

	public SignatureLicenseAuthorizer(SignatureProvider signatureProvider,
			PrivateKeyProvider privateKeyProvider, Function<I, byte[]> converter) {
		this.signatureProvider = requireNonNull(signatureProvider);
		this.privateKeyProvider = requireNonNull(privateKeyProvider);
		this.converter = requireNonNull(converter);
	}

	@Override
	public License<I> authorize(I licenseInfo) {
		try {
			requireNonNull(licenseInfo);
			Signature signature = this.signatureProvider.getSignature();
			signature.initSign(this.privateKeyProvider.getPrivateKey());
			signature.update(this.converter.apply(licenseInfo));
			return new SimpleLicense<I>(licenseInfo, signature.sign());
		} catch (SignatureException e) {
			throw new LicenceException(e);
		} catch (InvalidKeyException e) {
			throw new LicenceException(e);
		}
	}

}
