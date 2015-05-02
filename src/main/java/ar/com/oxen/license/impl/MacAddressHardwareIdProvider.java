package ar.com.oxen.license.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import ar.com.oxen.license.api.HardwareIdProvider;
import ar.com.oxen.license.api.LicenceException;

/**
 * {@link HardwareIdProvider} implementation that reads localhost network
 * interface MAC address.
 */
public class MacAddressHardwareIdProvider implements HardwareIdProvider {
	@Override
	public byte[] getHardwareId() {
		try {
			return NetworkInterface
					.getByInetAddress(InetAddress.getLocalHost())
					.getHardwareAddress();
		} catch (SocketException e) {
			throw new LicenceException(e);
		} catch (UnknownHostException e) {
			throw new LicenceException(e);
		}
	}
}
