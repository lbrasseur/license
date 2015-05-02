package ar.com.oxen.license.impl;

import java.io.Serializable;

import javax.inject.Inject;

import ar.com.oxen.license.api.License;
import ar.com.oxen.license.api.LicenseAuthorizationValidator;
import ar.com.oxen.license.api.LicenseInfoValidator;
import ar.com.oxen.license.api.LicenseValidator;

/**
 * Default license validation implementation. It just performs both,
 * authorization and license info validations and returns an "and" over these
 * results.
 * 
 * @param <I>
 *            The license info type
 */
public class DefaultLicenseValidator<I extends Serializable> implements
		LicenseValidator<I> {
	private LicenseAuthorizationValidator<I> licenseAuthorizationValidator;
	private LicenseInfoValidator<I> licenseInfoValidator;

	@Inject
	public DefaultLicenseValidator(
			LicenseAuthorizationValidator<I> licenseAuthorizationValidator,
			LicenseInfoValidator<I> licenseInfoValidator) {
		super();
		this.licenseAuthorizationValidator = licenseAuthorizationValidator;
		this.licenseInfoValidator = licenseInfoValidator;
	}

	@Override
	public boolean validate(License<I> license) {
		return this.licenseAuthorizationValidator.validate(license)
				&& this.licenseInfoValidator.validate(license.getInfo());
	}
}
