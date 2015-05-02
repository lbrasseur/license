package ar.com.oxen.license.api;

/**
 * Generic license exception.
 */
public class LicenceException extends RuntimeException {
	private static final long serialVersionUID = -7171540004251631499L;

	public LicenceException(Throwable cause) {
		super(cause);
	}
}
