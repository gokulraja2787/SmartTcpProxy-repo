package org.gokapp.proxy.server.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.gokapp.proxy.server.thread.ProxyService;
import org.gokapp.proxy.server.vo.HostPortVO;

/**
 * @author grangarajan
 *
 */
public final class ServiceManager {

	/**
	 * Thread pool
	 */
	private final static ExecutorService threadPoolService;

	/**
	 * Service map
	 */
	private final static Map<Integer, ProxyService> serviceMap;

	static {
		threadPoolService = Executors.newFixedThreadPool(8);
		serviceMap = new HashMap<Integer, ProxyService>();
	}

	/**
	 * Starts and run the services
	 * 
	 * @param implCd
	 * @param servicePort
	 * @param whereTo
	 * @param serviceName
	 */
	public static void newService(Integer implCd, Integer servicePort,
			HostPortVO whereTo, String serviceName) {
		ProxyService service = new ProxyService(implCd, serviceName, whereTo,
				servicePort);
		if (serviceMap.containsKey(servicePort)) {
			stopService(servicePort);
		}
		Thread proxyService = new Thread(service, serviceName);
		serviceMap.put(servicePort, service);
		threadPoolService.submit(proxyService);
	}

	/**
	 * Stop the service
	 * 
	 * @param servicePort
	 */
	public static void stopService(Integer servicePort) {
		if (serviceMap.containsKey(servicePort)) {
			System.out.println("Stopping " + servicePort);
			serviceMap.get(servicePort).interrupt();
			serviceMap.remove(servicePort);
		}
	}

	/**
	 * Shutdown the service
	 */
	public static void shutdownService() {
		Set<Integer> serviceSet = serviceMap.keySet();
		for (Object port : serviceSet.toArray()) {
			stopService((Integer) port);
		}
		threadPoolService.shutdown();
	}

}
