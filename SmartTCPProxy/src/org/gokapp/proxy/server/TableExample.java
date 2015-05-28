package org.gokapp.proxy.server;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;

public class TableExample {
	
	private JScrollPane tableScrollPane; 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TableExample();
			}
		});
	}

	public TableExample() {
		JFrame guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Creating a Table Example");
		guiFrame.setSize(700, 200);

		guiFrame.setLocationRelativeTo(null);

		JTable table = new JTable(new ExampleTableModel());

		table.setAutoCreateRowSorter(true);
		table.setGridColor(Color.YELLOW);
		table.setBackground(Color.CYAN);
		String[] countries = { "Australia", "Brazil", "Canada", "China",
				"France", "Japan", "Norway", "Russia", "South Korea",
				"Tunisia", "USA" };
		JComboBox countryCombo = new JComboBox(countries);
		TableColumn countryColumn = table.getColumnModel().getColumn(2);
		countryColumn.setCellEditor(new DefaultCellEditor(countryCombo));

		TableColumn eventColumn = table.getColumnModel().getColumn(3);
		eventColumn.setPreferredWidth(150);
		TableColumn placeColumn = table.getColumnModel().getColumn(4);
		placeColumn.setPreferredWidth(5);
		tableScrollPane = new JScrollPane(table);
		guiFrame.add(tableScrollPane);
		guiFrame.setVisible(true);
	}
	
	public TableExample(boolean subShow){
		JTable table = new JTable(new ExampleTableModel());
		table.setAutoCreateRowSorter(true);
		table.setGridColor(Color.YELLOW);
		table.setBackground(Color.CYAN);
		String[] countries = { "Australia", "Brazil", "Canada", "China",
				"France", "Japan", "Norway", "Russia", "South Korea",
				"Tunisia", "USA" };
		JComboBox countryCombo = new JComboBox(countries);
		TableColumn countryColumn = table.getColumnModel().getColumn(2);
		countryColumn.setCellEditor(new DefaultCellEditor(countryCombo));

		TableColumn eventColumn = table.getColumnModel().getColumn(3);
		eventColumn.setPreferredWidth(150);
		TableColumn placeColumn = table.getColumnModel().getColumn(4);
		placeColumn.setPreferredWidth(5);
		tableScrollPane = new JScrollPane(table);
	}
	
	public JScrollPane getTableScrollPane(){
		return tableScrollPane;
	}

}

class ExampleTableModel extends AbstractTableModel {
	private String[] columnNames = { "First Name", "Surname", "Country", "Event",
			"Place", "Time", "World Record" };
	private Object[][] data = {
			{ "César Cielo", "Filho", "Brazil", "50m freestyle", 1, "21.30",
					false },
			{ "Amaury", "Leveaux", "France", "50m freestyle", 2, "21.45", false },
			{ "Alain", "Bernard", "France", "50m freestyle", 3, "21.49", false },
			{ "Alain", "Bernard", "France", "100m freestyle", 1, "47.21", false },
			{ "Eamon", "Sullivan", "Australia", "100m freestyle", 2, "47.32",
					false },
			{ "Jason", "Lezak", "USA", "100m freestyle", 3, "47.67", false },
			{ "César Cielo", "Filho", "Brazil", "100m freestyle", 3, "47.67",
					false },
			{ "Michael", "Phelps", "USA", "200m freestyle", 1, "1:42.96", true },
			{ "Park", "Tae-Hwan", "South Korea", "200m freestyle", 2,
					"1:44.85", false },
			{ "Peter", "Vanderkaay", "USA", "200m freestyle", 3, "1:45.14",
					false },
			{ "Park", "Tae-Hwan", "South Korea", "400m freestyle", 1,
					"3:41.86", false },
			{ "Zhang", "Lin", "China", "400m freestyle", 2, "3:42.44", false },
			{ "Larsen", "Jensen", "USA", "400m freestyle", 3, "3:42.78", false },
			{ "Oussama", "Mellouli", "Tunisia", "1500m freestyle", 1,
					"14:40.84", false },
			{ "Grant", "Hackett", "Australia", "1500m freestyle", 2,
					"14:41.53", false },
			{ "Ryan", "Cochrane", "Canada", "1500m freestyle", 3, "14:42.69",
					false },
			{ "Aaron", "Peirsol", "USA", "100m backstroke", 1, "52.54", true },
			{ "Matt", "Grevers", "USA", "100m backstroke", 2, "53.11", false },
			{ "Arkady", "Vyatchanin", "Russia", "100m backstroke", 3, "53.18",
					false },
			{ "Hayden", "Stoeckel", "Australia", "100m freestyle", 3, "53.18",
					false },
			{ "Ryan", "Lochte", "USA", "200m backstroke", 1, "1:53.94", true },
			{ "Aaron", "Peirsol", "USA", "200m backstroke", 2, "1:54.33", false },
			{ "Arkady", "Vyatchanin", "Russia", "200m backstroke", 3,
					"1:54.93", false },
			{ "Kosuke", "Kitajima", "Japan", "100m breaststroke", 1, "58.91",
					true },
			{ "Alexander", "Dale Oen", "Norway", "100m breaststroke", 2,
					"59.20", false },
			{ "Hugues", "Duboscq", "France", "100m breaststroke", 3, "59.37",
					false } };

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return data[row][column];
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 0 || column == 1) {
			return false;
		} else {
			return true;
		}
	}
}