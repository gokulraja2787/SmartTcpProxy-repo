package org.gokapp.proxy.server.manager;

import org.gokapp.proxy.server.Main;
import org.gokapp.proxy.server.constants.ProxyConstants;

/**
 * @author grangarajan
 *
 */
public class ExitManager {

	/**
	 * Manages global exit
	 * 
	 * @param statusCode
	 */
	public static void globalExit(int statusCode) {
		switch (statusCode) {
		case ProxyConstants.SAFE_EXIT_CODE:
			// TODO Log normal exit
			System.out.println("Terminated safely....");
			break;
		default:
			// TODO Log abnormal system exit
			System.out.println("UnSafe termination with code: " + statusCode);
		}
		try {
			ClientManager.shutdownService();
		} catch (Exception e) {
			// TODO Log went wrong
			e.printStackTrace();
			System.out.println(ProxyConstants.WARNING + e.getMessage()
					+ " while shutting down client manager");
		}
		try {
			ServiceManager.shutdownService();
		} catch (Exception e) {
			// TODO Log went wrong
			e.printStackTrace();
			System.out.println(ProxyConstants.WARNING + e.getMessage()
					+ " while shutting down service manager");
		}
		try {
			Main.getInstance().getGUI().shutdownEvent(statusCode);
		} catch (Exception e) {
			// TODO Log went wrong
			e.printStackTrace();
			System.out.println(ProxyConstants.WARNING + e.getMessage()
					+ " while shutting down window event manager");
		}
		System.exit(statusCode);
	}

}
