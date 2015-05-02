package ar.com.oxen.license.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import ar.com.oxen.license.api.LicenceException;

/**
 * KeyStore provider that reads KeyStore from a File.
 */
public class FileKeyStoreProvider extends AbstractKeyStoreProvider {
	private File keyStoreFile;

	public FileKeyStoreProvider(File keyStoreFile, String keyStorePassword) {
		super(keyStorePassword);
		this.keyStoreFile = keyStoreFile;
	}

	@Override
	protected InputStream openKeyStoreInputStream() {
		try {
			return new FileInputStream(this.keyStoreFile);
		} catch (FileNotFoundException e) {
			throw new LicenceException(e);
		}
	}
}
