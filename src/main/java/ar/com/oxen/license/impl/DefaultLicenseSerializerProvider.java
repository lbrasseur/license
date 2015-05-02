package ar.com.oxen.license.impl;

import java.io.Serializable;

import javax.inject.Provider;

import ar.com.oxen.commons.converter.api.Converter;
import ar.com.oxen.commons.converter.api.ConverterBuilder;
import ar.com.oxen.commons.converter.impl.Base64ToBytesConverter;
import ar.com.oxen.commons.converter.impl.BytesToBase64Converter;
import ar.com.oxen.commons.converter.impl.BytesToSerializableConverter;
import ar.com.oxen.commons.converter.impl.SaltConverter;
import ar.com.oxen.commons.converter.impl.SerializableToBytesConverter;
import ar.com.oxen.commons.converter.impl.UnsaltConverter;
import ar.com.oxen.license.api.License;
import ar.com.oxen.license.api.LicenseSerializer;

/**
 * JSR330 provider for creating a default {@link LicenseSerializer}
 * implementation, using converters.
 * 
 * @param <I>
 *            The license info type
 */
public class DefaultLicenseSerializerProvider<I extends Serializable>
		implements Provider<LicenseSerializer<I>> {
	@Override
	public LicenseSerializer<I> get() {
		Converter<I, String> infoToStringConverter = ConverterBuilder
				.create(new SerializableToBytesConverter<I>())
				.add(new SaltConverter()).add(new BytesToBase64Converter())
				.build();
		Converter<License<I>, String> licenseToStringConverter = ConverterBuilder
				.create(new SerializableToBytesConverter<License<I>>())
				.add(new SaltConverter()).add(new BytesToBase64Converter())
				.build();
		Converter<String, I> stringToInfoConverter = ConverterBuilder
				.create(new Base64ToBytesConverter())
				.add(new UnsaltConverter())
				.add(new BytesToSerializableConverter<I>()).build();
		Converter<String, License<I>> stringToLicenseConverter = ConverterBuilder
				.create(new Base64ToBytesConverter())
				.add(new UnsaltConverter())
				.add(new BytesToSerializableConverter<License<I>>()).build();

		return new ConverterLicenseSerializer<I>(infoToStringConverter,
				licenseToStringConverter, stringToInfoConverter,
				stringToLicenseConverter);
	}
}
