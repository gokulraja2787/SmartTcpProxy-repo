package org.gokapp.proxy.server.ui.simpleui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author grangarajan
 *
 */
public class CustomButtonEvent implements ActionListener {

	/**
	 * Holds runnable event on action
	 */
	private Runnable event;

	/**
	 * @param event
	 */
	public CustomButtonEvent(Runnable event) {
		super();
		this.event = event;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (null != event) {
			event.run();
		}

	}

}
