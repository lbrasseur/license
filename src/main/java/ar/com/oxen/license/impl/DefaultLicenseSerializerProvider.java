package ar.com.oxen.license.impl;

import java.io.Serializable;
import java.util.function.Function;

import javax.inject.Provider;

import ar.com.oxen.license.api.License;
import ar.com.oxen.license.api.LicenseSerializer;
import ar.com.oxen.license.impl.function.Base64ToBytesFunction;
import ar.com.oxen.license.impl.function.BytesToBase64Function;
import ar.com.oxen.license.impl.function.BytesToSerializableFunction;
import ar.com.oxen.license.impl.function.SaltFunction;
import ar.com.oxen.license.impl.function.SerializableToBytesFunction;
import ar.com.oxen.license.impl.function.UnsaltFunction;

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
		Function<I, String> infoToStringFunction = new SerializableToBytesFunction<I>()
				.andThen(new SaltFunction())
				.andThen(new BytesToBase64Function());
		Function<License<I>, String> licenseToStringFunction = new SerializableToBytesFunction<License<I>>()
				.andThen(new SaltFunction())
				.andThen(new BytesToBase64Function());
		Function<String, I> stringToInfoFunction = new Base64ToBytesFunction()
				.andThen(new UnsaltFunction())
				.andThen(new BytesToSerializableFunction<I>());
		Function<String, License<I>> stringToLicenseFunction = new Base64ToBytesFunction()
				.andThen(new UnsaltFunction())
				.andThen(new BytesToSerializableFunction<License<I>>());

		return new FunctionLicenseSerializer<I>(infoToStringFunction,
				licenseToStringFunction, stringToInfoFunction,
				stringToLicenseFunction);
	}
}
