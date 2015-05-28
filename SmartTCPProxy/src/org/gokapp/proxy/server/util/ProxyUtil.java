package org.gokapp.proxy.server.util;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import org.gokapp.proxy.server.constants.ProxyConstants;

/**
 * Helper Utility. Cannot extend to avoid Inheritance.
 * 
 * @author grangarajan
 *
 */
public final class ProxyUtil {

	/**
	 * Shows Error dialog
	 * 
	 * @param msg
	 * @param parent
	 */
	public static void showErrorDialog(String msg, JComponent parent) {
		JOptionPane.showMessageDialog(parent, msg, ProxyConstants.GLOBAL_TITLE,
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Show Info dialog
	 * 
	 * @param msg
	 * @param parent
	 */
	public static void showInfoDialog(String msg, JComponent parent) {
		JOptionPane.showMessageDialog(parent, msg, ProxyConstants.GLOBAL_TITLE,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Show confirm dialog
	 * 
	 * @param msg
	 * @param parent
	 * @return true if use chose yes else false
	 */
	public static boolean showConfirmDialog(String msg, JComponent parent) {
		int confirm = JOptionPane.showConfirmDialog(parent, msg,
				ProxyConstants.GLOBAL_TITLE, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE);
		switch (confirm) {
		case JOptionPane.OK_OPTION:
			return true;
		case JOptionPane.CANCEL_OPTION:
		default:
			return false;
		}
	}
}
