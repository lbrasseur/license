package ar.com.oxen.license.impl;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;

import ar.com.oxen.commons.converter.api.ConversionException;
import ar.com.oxen.commons.converter.api.Converter;
import ar.com.oxen.license.api.LicenceException;
import ar.com.oxen.license.api.License;
import ar.com.oxen.license.api.LicenseAuthorizer;
import ar.com.oxen.license.api.PrivateKeyProvider;
import ar.com.oxen.license.api.SignatureProvider;

/**
 * {@link LicenseAuthorizer} based on digital signatures and
 * {@link Converter}.
 * 
 * @param <I>
 *            The license info type
 */
public class SignatureLicenseAuthorizer<I extends Serializable> implements
		LicenseAuthorizer<I> {
	private SignatureProvider signatureProvider;
	private PrivateKeyProvider privateKeyProvider;
	private Converter<I, byte[]> converter;

	public SignatureLicenseAuthorizer(SignatureProvider signatureProvider,
			PrivateKeyProvider privateKeyProvider,
			Converter<I, byte[]> converter) {
		super();
		this.signatureProvider = signatureProvider;
		this.privateKeyProvider = privateKeyProvider;
		this.converter = converter;
	}

	@Override
	public License<I> authorize(I licenseInfo) {
		try {
			Signature signature = this.signatureProvider.getSignature();
			signature.initSign(this.privateKeyProvider.getPrivateKey());
			signature.update(this.converter.convert(licenseInfo));
			return new SimpleLicense<I>(licenseInfo, signature.sign());
		} catch (SignatureException e) {
			throw new LicenceException(e);
		} catch (InvalidKeyException e) {
			throw new ConversionException(e);
		}
	}

}
