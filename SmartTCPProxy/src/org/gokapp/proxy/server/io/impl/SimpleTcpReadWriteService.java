package org.gokapp.proxy.server.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.gokapp.proxy.server.io.GenericReadWriteService;

/**
 * Simple TCP Read Write Service
 * 
 * @author grangarajan
 *
 */
public class SimpleTcpReadWriteService extends GenericReadWriteService {

	/**
	 * 
	 * @param ipStream
	 * @param opStream
	 * @param remoteHost
	 * @param remotePort
	 * @param commSide
	 */
	public SimpleTcpReadWriteService(InputStream ipStream,
			OutputStream opStream, String remoteHost, int remotePort,
			COMM_IDENTIFIER commSide) {
		super(ipStream, opStream, remoteHost, remotePort, commSide);
	}

	/**
	 * Implementation Name
	 */
	private static final String IMPL_NAME = "Simple TCP IO";


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gokapp.proxy.server.thread.GenericReadWriteService#run()
	 */
	@Override
	public void run() {
		InputStream ipStream = getIpStream();
		OutputStream opStream = getOpStream();
		byte buf[];
		int read = -1;
		try {
			do {
				buf = new byte[2048];

				read = ipStream.read(buf);
				if (read == -1) {
					break;
				} else {
					opStream.write(buf, 0, read);
					opStream.flush();
				}
			} while (true);
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
