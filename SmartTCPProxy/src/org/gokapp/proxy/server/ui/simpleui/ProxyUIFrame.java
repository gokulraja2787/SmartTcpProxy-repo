/**
 * 
 */
package org.gokapp.proxy.server.ui.simpleui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.gokapp.proxy.server.constants.ProxyConstants;

/**
 * @author grangarajan
 *
 */
public class ProxyUIFrame extends JFrame implements Runnable {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 2153836515437713745L;

	/**
	 * Title is constant
	 * 
	 * @throws HeadlessException
	 */
	public ProxyUIFrame() throws HeadlessException {
		super(ProxyConstants.GLOBAL_TITLE);
	}

	/**
	 * Title is constant
	 * 
	 * @param gc
	 */
	public ProxyUIFrame(GraphicsConfiguration gc) {
		super(ProxyConstants.GLOBAL_TITLE, gc);
	}

	/**
	 * Tweaked Title
	 * 
	 * @param title
	 * @param gc
	 */
	public ProxyUIFrame(String title, GraphicsConfiguration gc) {
		super(ProxyConstants.GLOBAL_TITLE + ProxyConstants.DELIMIT + title, gc);
	}

	/**
	 * Tweaked title
	 * 
	 * @param title
	 * @throws HeadlessException
	 */
	public ProxyUIFrame(String title) throws HeadlessException {
		super(ProxyConstants.GLOBAL_TITLE + ProxyConstants.DELIMIT + title);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		setVisible(true); // Show visible
	}

}
