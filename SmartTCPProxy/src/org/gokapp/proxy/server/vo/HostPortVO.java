/**
 * 
 */
package org.gokapp.proxy.server.vo;

import java.io.Serializable;

import javax.swing.JButton;

/**
 * Represents Host and port
 * 
 * @author grangarajan
 *
 */
public class HostPortVO implements Serializable {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 3343754942619323516L;

	/**
	 * Port number
	 */
	private int port;

	/**
	 * Host name
	 */
	private String host;
	
	/**
	 * Remove button
	 */
	private JButton removeEntry;

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 * @param host
	 */
	public HostPortVO(int port, String host) {
		super();
		this.port = port;
		this.host = host;
		removeEntry = new JButton("Remove");
	}
	
	/**
	 * Default Constructor
	 */
	public HostPortVO(){
		super();
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the removeEntry
	 */
	public JButton getRemoveEntry() {
		return removeEntry;
	}

	/**
	 * @param removeEntry the removeEntry to set
	 */
	public void setRemoveEntry(JButton removeEntry) {
		this.removeEntry = removeEntry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + port;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HostPortVO other = (HostPortVO) obj;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (port != other.port)
			return false;
		return true;
	}

}
