package ar.com.oxen.license.impl;

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
	private String customerName;
	private String module;
	private Date expirationDate;
	private String code;
	private byte[] hardwareId;


	public DefaultLicenseInfo(String customerName, String module,
			Date expirationDate, String code, byte[] hardwareId) {
		super();
		this.customerName = customerName;
		this.module = module;
		this.expirationDate = expirationDate;
		this.code = code;
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
