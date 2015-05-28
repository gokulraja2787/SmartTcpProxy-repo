package org.gokapp.proxy.server.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.gokapp.proxy.server.io.impl.Mock421SMTPReadWriteService;
import org.gokapp.proxy.server.io.impl.MockSMTPReadWrite512Service;
import org.gokapp.proxy.server.io.impl.SimpleTcpReadWriteService;

/**
 * ReadWrite Service factory
 * 
 * @author grangarajan
 *
 */
public final class ReadWriteServiceFactory {

	/**
	 * Map to hold IO implementation
	 */
	private static Map<Integer, Class<? extends GenericReadWriteService>> implMap;

	private static ReadWriteServiceFactory selfInstance = null;

	/**
	 * Use {@link getFactoryInstance} to get an instance
	 */
	private ReadWriteServiceFactory() {
		implMap = new HashMap<Integer, Class<? extends GenericReadWriteService>>();
		// TODO externalize the available definition.
		implMap.put(1, SimpleTcpReadWriteService.class);
		implMap.put(2, Mock421SMTPReadWriteService.class);
		implMap.put(3, MockSMTPReadWrite512Service.class);

	}

	/**
	 * Givens an instance
	 * 
	 * @return {@link ReadWriteServiceFactory} instance
	 */
	public static ReadWriteServiceFactory getFactoryInstance() {
		if (null == selfInstance) {
			selfInstance = new ReadWriteServiceFactory();
		}
		return selfInstance;
	}

	/**
	 * 
	 * @param implCd
	 * @param ipStream
	 * @param opStream
	 * @param remoteHost
	 * @param remotePort
	 * @param commSide
	 * @return
	 */
	public GenericReadWriteService getIOServiceInstance(Integer implCd,
			InputStream ipStream, OutputStream opStream, String remoteHost,
			int remotePort, GenericReadWriteService.COMM_IDENTIFIER commSide) {
		Class<? extends GenericReadWriteService> clasz = implMap.get(implCd);
		GenericReadWriteService result = null;
		System.out.println("Getting for: " + implCd + " is "
				+ clasz.getCanonicalName());
		try {
			result = clasz.getConstructor(InputStream.class,
					OutputStream.class, String.class, int.class,
					GenericReadWriteService.COMM_IDENTIFIER.class).newInstance(
					ipStream, opStream, remoteHost, remotePort, commSide);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO log the error
			e.printStackTrace();
			System.err.println("Error while getting IO service instance: "
					+ e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @return
	 */
	public Map<Integer, Class<? extends GenericReadWriteService>> getAvailableIOService() {
		return implMap;
	}

}
