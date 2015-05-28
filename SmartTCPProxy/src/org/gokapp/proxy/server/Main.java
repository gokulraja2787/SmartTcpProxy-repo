package org.gokapp.proxy.server;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.gokapp.proxy.server.constants.ProxyConstants;
import org.gokapp.proxy.server.manager.ServiceManager;
import org.gokapp.proxy.server.ui.GUIFactory;
import org.gokapp.proxy.server.ui.ProxyAppUI;
import org.gokapp.proxy.server.vo.HostPortVO;

/**
 * @author grangarajan
 *
 */
public class Main {

	/**
	 * Self reference for singleton object
	 */
	private static Main selfInstance;

	/**
	 * Holds UI
	 */
	private ProxyAppUI ui;

	/**
	 * Map to hold: Key = Listening port Value = Forwarding HostPort
	 */
	private Map<Integer, HostPortVO> portFowardMap;

	/**
	 * Singleton
	 */
	private Main() {
		selfInstance = this;
		portFowardMap = new LinkedHashMap<Integer, HostPortVO>();
		ui = GUIFactory.getInstance().getAppUI();
		ui.startEvent();
		System.out.println("UI Init done....");
	}

	/**
	 * Get self instance
	 * 
	 * @return Main instance
	 */
	public static Main getInstance() {
		return selfInstance;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}

	/**
	 * Get number running service
	 * 
	 * @return no. active proxy service
	 */
	public int getProxyServiceCount() {
		if (portFowardMap != null) {
			return portFowardMap.size();
		}
		return 0;
	}

	/*
	 * Get item by index :)
	 */
	public Object __getItemByIndex(int row, int column) {
		Object ret = null;
		if (getProxyServiceCount() > 0
				&& (column < ProxyConstants.TABLE_COLUMN_COUNT && row < getProxyServiceCount())) {

			List<Entry<Integer, HostPortVO>> itemList = new ArrayList<Map.Entry<Integer, HostPortVO>>(
					portFowardMap.entrySet());
			Entry<Integer, HostPortVO> result = itemList.get(row);
			switch (column) {
			case 0:
				ret = result.getKey();
				break;
			case 1:
				ret = result.getValue().getHost();
				break;
			case 2:
				ret = result.getValue().getPort();
				break;
			case 3:
				ret = result.getValue();
				break;
			}

		}
		return ret;
	}

	/**
	 * Setup new port forward
	 * 
	 * @param implCd
	 * @param listeningPort
	 * @param destinationHost
	 * @param destinationPort
	 */
	public void setupPortForward(Integer implCd, Integer listeningPort,
			String destinationHost, int destinationPort) {
		HostPortVO destination = null;
		if (null != portFowardMap) {
			destination = new HostPortVO(destinationPort, destinationHost);
			portFowardMap.put(listeningPort, destination);
		}
		getGUI().fireDataChange();
		ServiceManager.newService(implCd, listeningPort, destination,
				(destinationHost + listeningPort));
	}

	/**
	 * Remove the port forward
	 * 
	 * @param listeningPort
	 */
	public void removePortForward(Integer listeningPort) {
		if (null != portFowardMap) {
			portFowardMap.remove(listeningPort);
		}
		getGUI().fireDataChange();
		ServiceManager.stopService(listeningPort);
	}

	/**
	 * Get GUI
	 * 
	 * @return get GUI
	 */
	public ProxyAppUI getGUI() {
		return ui;
	}
}
