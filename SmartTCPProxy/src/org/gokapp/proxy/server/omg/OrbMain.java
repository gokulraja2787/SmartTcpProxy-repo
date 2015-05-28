package org.gokapp.proxy.server.omg;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 * @author grangarajan
 *
 */

public class OrbMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(null == args || args.length < 2) {
			args = new String[]{"-ORBInitialPort 1050", "-ORBInitialHost EOE-MAL-2-108"};
		}
		ORB orb = ORB.init(args, null);

		try {
		POA rootpoa = POAHelper.narrow(orb
				.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			
			
			Servant proxyServerServant = new ProxyServerImpl();
			
			org.omg.CORBA.Object objRef = rootpoa.servant_to_reference(proxyServerServant);
			
			
			
		} catch (AdapterInactive | InvalidName | ServantNotActive | WrongPolicy e) {
			e.printStackTrace();
		}

		

	}

}
