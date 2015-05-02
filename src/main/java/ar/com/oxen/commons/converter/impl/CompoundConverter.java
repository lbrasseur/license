package ar.com.oxen.commons.converter.impl;

import ar.com.oxen.commons.converter.api.Converter;

/**
 * Converter that chains two converters.
 * 
 * @param <S>
 *            The source type
 * @param <M>
 *            Teh "middle" type: target for first converter, source for the
 *            second one.
 * @param <T>
 *            The target type
 */
public class CompoundConverter<S, M, T> implements Converter<S, T> {
	private Converter<S, M> sourceConverter;
	private Converter<M, T> targetConverter;

	public CompoundConverter(Converter<S, M> sourceConverter,
			Converter<M, T> targetConverter) {
		super();
		this.sourceConverter = sourceConverter;
		this.targetConverter = targetConverter;
	}

	@Override
	public T convert(S source) {
		return this.targetConverter.convert(this.sourceConverter
				.convert(source));
	}
}
