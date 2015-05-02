package ar.com.oxen.commons.converter.api;

/**
 * Generic conversion exception.
 */
public class ConversionException extends RuntimeException {
	private static final long serialVersionUID = -4900667084054070877L;

	public ConversionException(Throwable cause) {
		super(cause);
	}
}
