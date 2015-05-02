package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

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
	private final LicenseAuthorizationValidator<I> licenseAuthorizationValidator;
	private final LicenseInfoValidator<I> licenseInfoValidator;

	@Inject
	public DefaultLicenseValidator(
			LicenseAuthorizationValidator<I> licenseAuthorizationValidator,
			LicenseInfoValidator<I> licenseInfoValidator) {
		this.licenseAuthorizationValidator = requireNonNull(licenseAuthorizationValidator);
		this.licenseInfoValidator = requireNonNull(licenseInfoValidator);
	}

	@Override
	public boolean validate(License<I> license) {
		requireNonNull(license);
		return licenseAuthorizationValidator.validate(license)
				&& licenseInfoValidator.validate(license.getInfo());
	}
}
