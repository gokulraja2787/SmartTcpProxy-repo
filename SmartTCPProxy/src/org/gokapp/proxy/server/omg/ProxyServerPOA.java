package org.gokapp.proxy.server.omg;


/**
* org/gokapp/proxy/server/omg/ProxyServerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ProxyServer.idl
* Friday, May 15, 2015 10:25:41 AM EDT
*/

@SuppressWarnings("unchecked")
public abstract class ProxyServerPOA extends org.omg.PortableServer.Servant
 implements org.gokapp.proxy.server.omg.ProxyServerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  @SuppressWarnings("rawtypes")
private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("startup", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // org/gokapp/proxy/server/omg/ProxyServer/startup
       {
         this.startup ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:org/gokapp/proxy/server/omg/ProxyServer:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ProxyServer _this() 
  {
    return ProxyServerHelper.narrow(
    super._this_object());
  }

  public ProxyServer _this(org.omg.CORBA.ORB orb) 
  {
    return ProxyServerHelper.narrow(
    super._this_object(orb));
  }


} // class ProxyServerPOA