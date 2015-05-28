package org.gokapp.proxy.server.ui.simpleui;

import org.gokapp.proxy.server.ui.ProxyAppUI;

/**
 * Factory to create simple UI
 * 
 * @author grangarajan
 *
 */
public final class SimpleUIFactory {

	/**
	 * Simple UI Instance
	 */
	private static final SimpleUI oneInstance;

	static {
		oneInstance = new SimpleUI();
	}

	/**
	 * Get instance of Simple UI
	 * 
	 * @return ProxyAppUI
	 */
	public static ProxyAppUI getSimpleUIInstance() {
		return oneInstance;
	}

}
