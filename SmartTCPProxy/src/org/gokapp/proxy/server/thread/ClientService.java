package org.gokapp.proxy.server.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.gokapp.proxy.server.constants.ProxyConstants;
import org.gokapp.proxy.server.io.GenericReadWriteService;
import org.gokapp.proxy.server.io.ReadWriteServiceFactory;

/**
 * Actual "port forwarder"; This medium talks between remote and client
 * 
 * @author grangarajan
 *
 */
public class ClientService implements Runnable {

	/**
	 * Holds the remote address
	 */
	private String remoteHost;
	/**
	 * Holds the remote port
	 */
	private int remotePort;
	/**
	 * Holds the client socket
	 */
	private Socket client;
	/**
	 * Holds IO implementation code
	 */
	private Integer implCd;

	/**
	 * 
	 * @param remoteHost
	 * @param remotePort
	 * @param client
	 * @param serviceType
	 */
	public ClientService(Integer implCd, String remoteHost, int remotePort,
			Socket client) {
		super();
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
		this.client = client;
		this.implCd = implCd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Socket __proxy = null;
		try {
			__proxy = new Socket(InetAddress.getByName(remoteHost), remotePort);
			final InputStream ipStream = __proxy.getInputStream();
			final OutputStream opStream = __proxy.getOutputStream();
			final InputStream clientIpStream = client.getInputStream();
			final OutputStream clientOpStream = client.getOutputStream();
			GenericReadWriteService clientServerSession = ReadWriteServiceFactory
					.getFactoryInstance()
					.getIOServiceInstance(
							implCd,
							clientIpStream,
							opStream,
							remoteHost,
							remotePort,
							GenericReadWriteService.COMM_IDENTIFIER.CLIENT_SERVER);
			Thread t1 = new Thread(clientServerSession);
			GenericReadWriteService serverClientSession = ReadWriteServiceFactory
					.getFactoryInstance()
					.getIOServiceInstance(
							implCd,
							ipStream,
							clientOpStream,
							remoteHost,
							remotePort,
							GenericReadWriteService.COMM_IDENTIFIER.SERVER_CLIENT);
			Thread t2 = new Thread(serverClientSession);
			t1.start();
			t2.start();
			try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				System.err.println("Communication error: " + e.getMessage());
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(remoteHost + ": " + remotePort
					+ "Communication exception " + e.getMessage());
		} finally {
			try {
				if (null != __proxy) {
					__proxy.close();
				}
				if (null != client) {
					client.close();
				}
			} catch (IOException e) {
				// TODO log Safe here
				e.printStackTrace();
				System.out.println(ProxyConstants.WARNING + e.getMessage()
						+ " while stopping the service ");
			}
		}
	}

}
