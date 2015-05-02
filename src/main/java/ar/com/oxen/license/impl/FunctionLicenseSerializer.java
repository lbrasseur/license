package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.util.function.Function;

import ar.com.oxen.license.api.License;
import ar.com.oxen.license.api.LicenseSerializer;

/**
 * A {@link Function}-based license serializer. It receives 4 converters (for
 * both, license and info, in both ways).
 * 
 * @param <I>
 *            The license info type
 */
public class FunctionLicenseSerializer<I extends Serializable> implements
		LicenseSerializer<I> {
	private final Function<I, String> infoToStringFunction;
	private final Function<License<I>, String> licenseToStringFunction;
	private final Function<String, I> stringToInfoFunction;
	private final Function<String, License<I>> stringToLicenseFunction;

	public FunctionLicenseSerializer(Function<I, String> infoToStringFunction,
			Function<License<I>, String> licenseToStringFunction,
			Function<String, I> stringToInfoFunction,
			Function<String, License<I>> stringToLicenseFunction) {
		this.infoToStringFunction = requireNonNull(infoToStringFunction);
		this.licenseToStringFunction = requireNonNull(licenseToStringFunction);
		this.stringToInfoFunction = requireNonNull(stringToInfoFunction);
		this.stringToLicenseFunction = requireNonNull(stringToLicenseFunction);
	}

	@Override
	public String serializeLicenceInfo(I licenseInfo) {
		requireNonNull(licenseInfo);
		return infoToStringFunction.apply(licenseInfo);
	}

	@Override
	public String serializeLicence(License<I> license) {
		requireNonNull(license);
		return licenseToStringFunction.apply(license);
	}

	@Override
	public I deserializeLicenceInfo(String data) {
		requireNonNull(data);
		return stringToInfoFunction.apply(data);
	}

	@Override
	public License<I> deserializeLicence(String data) {
		requireNonNull(data);
		return this.stringToLicenseFunction.apply(data);
	}
}
