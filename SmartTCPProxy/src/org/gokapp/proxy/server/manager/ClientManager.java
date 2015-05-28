package org.gokapp.proxy.server.manager;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.gokapp.proxy.server.thread.ClientService;

/**
 * @author grangarajan
 *
 */
public final class ClientManager {

	/**
	 * Thread pool
	 */
	private final static ExecutorService threadPoolService;

	static {
		threadPoolService = Executors.newCachedThreadPool();
	}

	public static void manageClient(Integer implCd, String remoteHost,
			int remotePort, Socket client) {
		ClientService service = new ClientService(implCd, remoteHost,
				remotePort, client);
		threadPoolService.submit(service);
	}

	/**
	 * Shutdown the service
	 */
	public static void shutdownService() {
		threadPoolService.shutdown();
	}

}
