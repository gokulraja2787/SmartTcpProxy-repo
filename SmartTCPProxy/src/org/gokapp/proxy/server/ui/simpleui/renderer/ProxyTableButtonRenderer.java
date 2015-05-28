package org.gokapp.proxy.server.ui.simpleui.renderer;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Renderes the table button
 * 
 * @author grangarajan
 *
 */
public class ProxyTableButtonRenderer implements TableCellRenderer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax
	 * .swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JButton button = (JButton) value;
		return button;
	}

}
