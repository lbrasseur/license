package ar.com.oxen.license.api;

/**
 * Identifies the underlying hardware. It should return always the same
 * identifier when running on the same host. However, according to the
 * implementation, the identifier could vary according to hardware configuration
 * changes.
 */
public interface HardwareIdProvider {
	/**
	 * @return The hardware identifier.
	 */
	byte[] getHardwareId();
}
