package ar.com.oxen.license.impl;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * A common-use license info, which includes a company name, hardware
 * identification and an expiration date. A "code" field is provided for generic
 * use.<br>
 * However, in most cases, this implementation will be discarded in favor of a
 * domain-specific implementation.
 */
public class DefaultLicenseInfo implements Serializable {
	private static final long serialVersionUID = -5962868381565454809L;
	private final String customerName;
	private final String module;
	private final Date expirationDate;
	private final String code;
	private final byte[] hardwareId;

	public DefaultLicenseInfo(String customerName, String module,
			Date expirationDate, String code, byte[] hardwareId) {
		this.customerName = requireNonNull(customerName);
		this.module = requireNonNull(module);
		this.expirationDate = requireNonNull(expirationDate);
		this.code = requireNonNull(code);
		this.hardwareId = hardwareId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getModule() {
		return module;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public String getCode() {
		return code;
	}

	public byte[] getHardwareId() {
		return hardwareId;
	}

	@Override
	public String toString() {
		return "DefaultLicenseInfo [customerName=" + customerName
				+ ", expirationDate=" + expirationDate + ", code=" + code
				+ ", hardwareId=" + Arrays.toString(hardwareId) + "]";
	}
}
