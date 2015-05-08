package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;
import ar.com.oxen.license.api.Function;
import ar.com.oxen.license.api.HardwareIdProvider;

/**
 * A decorator {@link HardwareIdProvider} which uses a {@link Function} in order
 * to transform the output.
 */
public class FunctionHardwareIdProvider implements HardwareIdProvider {
	private final HardwareIdProvider hardwareIdProvider;
	private final Function<byte[], byte[]> converter;

	public FunctionHardwareIdProvider(HardwareIdProvider hardwareIdProvider,
			Function<byte[], byte[]> converter) {
		this.hardwareIdProvider = requireNonNull(hardwareIdProvider);
		this.converter = requireNonNull(converter);
	}

	@Override
	public byte[] getHardwareId() {
		return converter.apply(hardwareIdProvider.getHardwareId());
	}
}
