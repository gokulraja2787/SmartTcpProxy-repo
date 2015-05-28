/**
 * 
 */
package org.gokapp.proxy.server.ui.simpleui;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.gokapp.proxy.server.Main;
import org.gokapp.proxy.server.constants.ProxyConstants;
import org.gokapp.proxy.server.vo.HostPortVO;

/**
 * Table model for UI Table
 * 
 * @author grangarajan
 *
 */
public class ProxyUITableModel extends AbstractTableModel {

	JTable parent;

	/**
	 * 
	 * @param parent
	 */
	public ProxyUITableModel(JTable parent) {
		this.parent = parent;
	}

	/**
	 * Serial Number
	 */
	private static final long serialVersionUID = -3360133354792435637L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return ProxyConstants.TABLE_COLUMN_COUNT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return Main.getInstance().getProxyServiceCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {
		Object result = Main.getInstance().__getItemByIndex(row, column);
		JButton retBut = null;
		if (column == 3 && null != result) {
			retBut = ((HostPortVO) result).getRemoveEntry();
			result = retBut;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false; // No cells are editable
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		return ProxyConstants.TABLE_COLUMNS[column];
	}

}
