package org.gokapp.proxy.server.omg;

/**
* org/gokapp/proxy/server/omg/ProxyServerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ProxyServer.idl
* Friday, May 15, 2015 10:26:07 AM EDT
*/

public final class ProxyServerHolder implements org.omg.CORBA.portable.Streamable
{
  public org.gokapp.proxy.server.omg.ProxyServer value = null;

  public ProxyServerHolder ()
  {
  }

  public ProxyServerHolder (org.gokapp.proxy.server.omg.ProxyServer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.gokapp.proxy.server.omg.ProxyServerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.gokapp.proxy.server.omg.ProxyServerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.gokapp.proxy.server.omg.ProxyServerHelper.type ();
  }

}
