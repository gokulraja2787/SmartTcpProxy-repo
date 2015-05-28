package org.gokapp.proxy.server.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.gokapp.proxy.server.constants.ProxyConstants;
import org.gokapp.proxy.server.io.GenericReadWriteService;

/**
 * Read write service for SMTP and mocks 421 response for every 3 request.
 * 
 * @author grangarajan
 *
 */
public class Mock421SMTPReadWriteService extends GenericReadWriteService {

	private static int connectionElapser = 3;
	private static boolean trans = false;

	/**
	 * Implementation name
	 */
	private static final String IMPL_NAME = "SMTP Mock Service";

	/**
	 * 
	 * @param ipStream
	 * @param opStream
	 * @param remoteHost
	 * @param remotePort
	 * @param commSide
	 */
	public Mock421SMTPReadWriteService(InputStream ipStream,
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
		InputStream ipStream = getIpStream();
		OutputStream opStream = getOpStream();
		byte buf[];
		int read = -1;
		try {
			if (getCommSide() == COMM_IDENTIFIER.CLIENT_SERVER) {
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
			} else if (getCommSide() == COMM_IDENTIFIER.SERVER_CLIENT) {
				if (!mock421()) {
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
				} else {
					buf = ProxyConstants.__421.getBytes();
					opStream.write(buf);
					opStream.flush();
				}
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

	/**
	 * Randomly pick on or off
	 * 
	 * @return true | false
	 */
	private boolean mock421() {
		if (connectionElapser == 0) {
			if (trans == false) {
				ExecutorService service = Executors.newSingleThreadExecutor();
				Runnable runner = new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(14000);
							//Thread.sleep(90000);
							//Thread.sleep(50000);
						} catch (InterruptedException e) {
							System.out
									.println("Waiting for 14000 to reconfigure SMTP");
						} finally {
							Mock421SMTPReadWriteService.trans = false;
						}
						Mock421SMTPReadWriteService.connectionElapser = 3;

					}
				};
				service.submit(runner);
				service.shutdown();
				trans = true;
			}
		}
		return connectionElapser-- <= 0;
	}

}
