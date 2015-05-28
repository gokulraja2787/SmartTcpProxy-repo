package org.gokapp.proxy.server.ui.simpleui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTable;

import org.gokapp.proxy.server.Main;
import org.gokapp.proxy.server.constants.ProxyConstants;
import org.gokapp.proxy.server.ui.simpleui.events.CustomButtonEvent;
import org.gokapp.proxy.server.util.ProxyUtil;

/**
 * Performs mouse operation on table click
 * 
 * @author grangarajan
 *
 */
public class ProxyTableMouseListener extends MouseAdapter {

	// Holds proxy table
	private final JTable proxyTable;

	/**
	 * @param proxyTable
	 */
	public ProxyTableMouseListener(JTable proxyTable) {
		super();
		this.proxyTable = proxyTable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		int col = proxyTable.getColumnModel().getColumnIndexAtX(event.getX());
		int row = event.getY() / proxyTable.getRowHeight();
		if (row < proxyTable.getRowCount() && row >= 0
				&& col < proxyTable.getColumnCount() && col >= 0) {
			Object value = proxyTable.getValueAt(row, col);
			final Integer servicePort = (Integer) proxyTable.getValueAt(row, 0);
			if (value instanceof JButton) {
				JButton removeEntryButton = (JButton) value;
				if (removeEntryButton.getActionListeners() == null
						|| removeEntryButton.getActionListeners().length == 0) {
					removeEntryButton.addActionListener(new CustomButtonEvent(
							new Runnable() {

								@Override
								public void run() {
									if (ProxyUtil
											.showConfirmDialog(
													ProxyConstants.REMOVE_ALERT
															+ servicePort
															+ ProxyConstants.REMOVE_SURE,
													proxyTable)) {
										Main.getInstance().removePortForward(
												servicePort);
									}
								}
							}));
				}
				removeEntryButton.doClick();
			}
		}
	}

}
