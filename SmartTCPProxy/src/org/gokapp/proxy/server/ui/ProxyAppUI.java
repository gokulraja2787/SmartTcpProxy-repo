package org.gokapp.proxy.server.ui;

/**
 * Defines default UI
 * @author grangarajan
 *
 */
public interface ProxyAppUI {

	/**
	 * Start UI Initialization
	 */
	public void startEvent();
	
	/**
	 * Call shutdown
	 * @param statusCode
	 */
	public void shutdownEvent(int statusCode);
	
	/**
	 * Fires data change
	 */
	public void fireDataChange();
	
}
