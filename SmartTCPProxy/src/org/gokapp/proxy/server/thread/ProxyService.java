package org.gokapp.proxy.server.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.gokapp.proxy.server.constants.ProxyConstants;
import org.gokapp.proxy.server.exception.ServiceException;
import org.gokapp.proxy.server.manager.ClientManager;
import org.gokapp.proxy.server.vo.HostPortVO;

/**
 * Represent a server thread
 * 
 * @author grangarajan
 *
 */
public class ProxyService implements Runnable {

	/**
	 * Holds service name
	 */
	private String serviceName;
	/**
	 * Holds destination
	 */
	private HostPortVO destination;
	/**
	 * Holds the port number in which this service has to run with
	 */
	private Integer servicePort;
	/**
	 * Represent current service
	 */
	private ServerSocket service;
	/**
	 * Impl Cd
	 */
	private Integer implCd;

	/**
	 * 
	 * @param implCd
	 * @param serviceName
	 * @param destination
	 * @param servicePort
	 */
	public ProxyService(Integer implCd, String serviceName,
			HostPortVO destination, Integer servicePort) {
		super();
		this.serviceName = serviceName;
		this.destination = destination;
		this.servicePort = servicePort;
		this.implCd = implCd;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		service = null;
		try {
			service = new ServerSocket(servicePort);
			Socket client = null;
			while (!Thread.currentThread().isInterrupted()) {
				client = service.accept();
				ClientManager.manageClient(implCd, destination.getHost(),
						destination.getPort(), client);
			}
		} catch (IOException e) {
			// TODO log service cannot be started
			e.printStackTrace();
			System.err.println(this.serviceName
					+ ProxyConstants.SERVICE_CANNOT_START + e.getMessage());
			throw new ServiceException(ProxyConstants.SERVICE_CANNOT_START
					+ e.getMessage(), e);
		} catch (Exception e) {
			// TODO log service cannot be started
			e.printStackTrace();
			System.err.println(this.serviceName
					+ ProxyConstants.SERVICE_CANNOT_RUN + e.getMessage());
			throw new ServiceException(ProxyConstants.SERVICE_CANNOT_START
					+ e.getMessage(), e);
		} finally {
			System.out.println(serviceName + " Stopped");
			if (null != service) {
				try {
					service.close();
				} catch (IOException e) {
					// TODO log service cannot run
					e.printStackTrace();
					System.out.println(ProxyConstants.WARNING + e.getMessage()
							+ " while stopping the service " + serviceName
							+ " on port " + servicePort);
				}
			}
		}

	}

	/**
	 * Interrupt the current thread
	 */
	public void interrupt() {
		Thread.currentThread().interrupt();
		try {
			service.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(ProxyConstants.WARNING + e.getMessage() + " on "
					+ serviceName);
		}
		System.out.println(serviceName + " interrupted");
	}

}
