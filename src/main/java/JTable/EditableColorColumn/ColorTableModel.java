package JTable.EditableColorColumn;

import java.awt.Color;

import javax.swing.table.AbstractTableModel;

public class ColorTableModel extends AbstractTableModel {

	Object rowData[][] = { { "1", "ichi - \u4E00", Boolean.TRUE, Color.RED },
			{ "2", "ni - \u4E8C", Boolean.TRUE, Color.BLUE }, { "3", "san - \u4E09", Boolean.FALSE, Color.GREEN },
			{ "4", "shi - \u56DB", Boolean.TRUE, Color.MAGENTA }, { "5", "go - \u4E94", Boolean.FALSE, Color.PINK }, };

	String columnNames[] = { "English", "Japanese", "Boolean", "Color" };

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowData.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return rowData[rowIndex][columnIndex];
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public Class getColumnClass(int column) {
		return (getValueAt(0, column).getClass());
	}

	public void setValueAt(Object value, int row, int column) {
		rowData[row][column] = value;
	}

	public boolean isCellEditable(int row, int column) {
		return (column != 0);
	}

}
