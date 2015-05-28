/**
 * 
 */
package org.gokapp.proxy.server.omg;

import org.gokapp.proxy.server.Main;

/**
 * @author grangarajan
 *
 */
class ProxyServerImpl extends ProxyServerPOA {

	/* (non-Javadoc)
	 * @see org.gokapp.proxy.server.omg.ProxyServerOperations#startup()
	 */
	@Override
	public void startup() {
		System.out.println("Starting up Smart TCP Proxy");
		Main.main(null);
	}

}
