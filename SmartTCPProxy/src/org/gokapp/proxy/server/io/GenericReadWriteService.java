package org.gokapp.proxy.server.io;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Does read write operation
 * 
 * @author grangarajan
 *
 */
public abstract class GenericReadWriteService implements Runnable {

	/**
	 * Identifies the side of communication
	 * 
	 * @author grangarajan
	 *
	 */
	public static enum COMM_IDENTIFIER {
		SERVER_CLIENT, CLIENT_SERVER;
	};

	private InputStream ipStream;
	private OutputStream opStream;

	private String remoteHost;
	private int remotePort;
	private COMM_IDENTIFIER commSide;

	/**
	 * 
	 * @param ipStream
	 * @param opStream
	 * @param remoteHost
	 * @param remotePort
	 * @param commSide
	 */
	public GenericReadWriteService(InputStream ipStream, OutputStream opStream,
			String remoteHost, int remotePort, COMM_IDENTIFIER commSide) {
		super();
		this.ipStream = ipStream;
		this.opStream = opStream;
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
		this.commSide = commSide;
	}

	/**
	 * @return the ipStream
	 */
	public InputStream getIpStream() {
		return ipStream;
	}

	/**
	 * @return the opStream
	 */
	public OutputStream getOpStream() {
		return opStream;
	}

	/**
	 * Underlaying implementers
	 */
	@Override
	public abstract void run();

	/**
	 * Says implementation name
	 * 
	 * @return Implementation Name
	 */
	public abstract String getImplName();

	/**
	 * @return the remoteHost
	 */
	public String getRemoteHost() {
		return remoteHost;
	}

	/**
	 * @return the remotePort
	 */
	public int getRemotePort() {
		return remotePort;
	}

	/**
	 * @return the commSide
	 */
	public COMM_IDENTIFIER getCommSide() {
		return commSide;
	}

}
