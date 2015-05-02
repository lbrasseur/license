package ar.com.oxen.license.api;

import java.io.Serializable;

/**
 * Validates a license. Usually, this will involve validating the info (a
 * domain-specific process) and validating the authorization (an
 * algorithm-specific process, for example, a DSA signature).
 * 
 * @param <I>
 *            The license info type
 */
public interface LicenseValidator<I extends Serializable> {
	/**
	 * Performs the validation.
	 * 
	 * @param license
	 *            The license
	 * @return true if the license is valid
	 */
	boolean validate(License<I> license);
}
