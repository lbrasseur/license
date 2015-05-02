package ar.com.oxen.license.api;

import java.security.PrivateKey;

/**
 * Component that provides a private key.
 */
public interface PrivateKeyProvider {
	/**
	 * @return The private key 
	 */
	PrivateKey getPrivateKey();
}
