package org.gokapp.proxy.server.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.gokapp.proxy.server.constants.ProxyConstants;
import org.gokapp.proxy.server.io.GenericReadWriteService;

/**
 * Read write service for SMTP and always mocks 512 response.
 * 
 * @author grangarajan
 *
 */
public class MockSMTPReadWrite512Service extends GenericReadWriteService {

	/**
	 * Implementation name
	 */
	private static final String IMPL_NAME = "SMTP Mock Service and respond as 512";

	/**
	 * 
	 * @param ipStream
	 * @param opStream
	 * @param remoteHost
	 * @param remotePort
	 * @param commSide
	 */
	public MockSMTPReadWrite512Service(InputStream ipStream,
			OutputStream opStream, String remoteHost, int remotePort,
			COMM_IDENTIFIER commSide) {
		super(ipStream, opStream, remoteHost, remotePort, commSide);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gokapp.proxy.server.io.GenericReadWriteService#run()
	 */
	@Override
	public void run() {
		OutputStream opStream = getOpStream();
		byte buf[];
		try {
			if (getCommSide() == COMM_IDENTIFIER.CLIENT_SERVER) {
				// Don't do anything
			} else if (getCommSide() == COMM_IDENTIFIER.SERVER_CLIENT) {
				// Always send 512 response
				buf = ProxyConstants.__512.getBytes();
				opStream.write(buf);
				opStream.flush();
			}
			opStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(getRemoteHost() + ": " + getRemotePort()
					+ "Communication exception " + e.getMessage());
		}
		buf = null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gokapp.proxy.server.io.GenericReadWriteService#getImplName()
	 */
	@Override
	public String getImplName() {
		return IMPL_NAME;
	}

}
