package ar.com.oxen.license.api;

import java.io.Serializable;

/**
 * Component used for serializing licenses to String and vice versa.
 * 
 * @param <I>
 *            The license info type
 */
public interface LicenseSerializer<I extends Serializable> {
	/**
	 * Converts license information into an String.
	 * 
	 * @param licenseInfo
	 *            The license information
	 * @return The String
	 */
	String serializeLicenceInfo(I licenseInfo);

	/**
	 * Converts license information into an String.
	 * 
	 * @param license
	 *            The license
	 * @return The String
	 */
	String serializeLicence(License<I> license);

	/**
	 * Reads license information from a String.
	 * 
	 * @param data
	 *            The String
	 * @return The license information
	 */
	I deserializeLicenceInfo(String data);

	/**
	 * Reads a license from a String.
	 * 
	 * @param data
	 *            The String
	 * @return The license
	 */
	License<I> deserializeLicence(String data);
}
