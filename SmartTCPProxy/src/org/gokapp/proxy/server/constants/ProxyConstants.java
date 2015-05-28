/**
 * 
 */
package org.gokapp.proxy.server.constants;

/**
 * Interface ProxyConstants holds only constants
 * 
 * @author grangarajan
 *
 */
public interface ProxyConstants {

	/**
	 * Title for all window
	 */
	String GLOBAL_TITLE = "Smart Proxy";

	/**
	 * Message to hold port forwarding
	 */
	String CONG_PORT_FWD = "Configure Port Forwarding";

	String DESTI_HOST = "Destination Host";

	String DESTI_PORT = "Destination Port";

	String LISTEN_MSG = "Port to listen for";
	
	String SELECT_SERVICE = "Select Service";

	String ADD_FWD_MSG = "Add port forwarding";

	String WRONG_PORT = "Entered Port is not valid!";
	
	String ACTIVE_SERVICE = "List of active service";

	String EMPTY = "";

	String REMOVE_ALERT = "Removing this entry will stop the following following service: ";
	String REMOVE_SURE = "Are you sure you want to continue?";

	/**
	 * Global delimit
	 */
	char DELIMIT = ':';

	String[] TABLE_COLUMNS = { "From Port", "To Host", "To Port", "Remove" };

	int TABLE_COLUMN_COUNT = 4;

	/**
	 * Exit status codes
	 */
	int SAFE_EXIT_CODE = 0;

	/**
	 * Error Messages
	 */
	String SERVICE_CANNOT_START = " cannot be started: ";

	String SERVICE_CANNOT_RUN = " cannot be run: ";

	String WARNING = "WARNING: ";
	
	String __421 = "421 Service not available";
	
	String __512 = "512 5.1 Bad address";

}
