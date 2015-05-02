package ar.com.oxen.license.api;

import java.io.Serializable;

/**
 * A license, which holds license information and an authorization. <br>
 * The license information is domain-specific, so it is parameterized using a
 * generic type.<br>
 * The authorization is implementation-specific (for example, a DSA signature).
 * 
 * @param <I>
 *            The license info type
 */
public interface License<I extends Serializable> extends Serializable {
	/**
	 * @return The license info.
	 */
	I getInfo();

	/**
	 * @return The authorization
	 */
	byte[] getAuthorization();
}
