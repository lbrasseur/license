package ar.com.oxen.commons.converter.api;

/**
 * A stateless component which transforms from an object to another
 * 
 * @param <S>
 *            The source type
 * @param <T>
 *            The target type
 */
public interface Converter<S, T> {
	/**
	 * Performs the conversion
	 * 
	 * @param source
	 *            The source object
	 * @return The converted (target) object
	 */
	T convert(S source);
}
