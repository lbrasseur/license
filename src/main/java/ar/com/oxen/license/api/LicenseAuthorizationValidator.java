package ar.com.oxen.license.api;

import java.io.Serializable;

/**
 * Component that validates the license authorization field against its license
 * info.
 * 
 * @param <I>
 *            The license info type
 */
public interface LicenseAuthorizationValidator<I extends Serializable> {
	/**
	 * Validates the authorization.
	 * 
	 * @param license
	 *            The license
	 * @return true if the authorization is ok
	 */
	boolean validate(License<I> license);
}
