package org.gokapp.proxy.server.ui;

import org.gokapp.proxy.server.ui.simpleui.SimpleUIFactory;

/**
 * Factory method to get GUI instance. Idea behind is to choose difference look
 * and feel.
 * 
 * @author grangarajan
 *
 */
public final class GUIFactory {

	/**
	 * Holds self instance
	 */
	private final static GUIFactory selfInstance;

	/**
	 * Initialize self instance
	 */
	static {
		selfInstance = new GUIFactory();
	}

	/**
	 * Cannot create instance of this
	 */
	private GUIFactory() {

	}

	/**
	 * Return self instance
	 * 
	 * @return GUIFactory instance
	 */
	public static GUIFactory getInstance() {
		return selfInstance;
	}

	/**
	 * Gets Default SimpleUI
	 * 
	 * @return ProxyAppUI Instance
	 */
	public ProxyAppUI getAppUI() {
		// TODO Externalize available UI and initialize UI via reflection
		return SimpleUIFactory.getSimpleUIInstance();
	}

}
