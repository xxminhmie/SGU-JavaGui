package JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class FixedTable {
	public static void main(String[] args) {
		final Object rowData[][] = { { "1", "one", "ichi", "un", "I", "\u4E00" },
				{ "1", "one", "ichi", "un", "I", "\u4E00" }, { "1", "one", "ichi", "un", "I", "\u4E00" },
				{ "1", "one", "ichi", "un", "I", "\u4E00" }, { "1", "one", "ichi", "un", "I", "\u4E00" },
				{ "1", "one", "ichi", "un", "I", "\u4E00" }, { "1", "one", "ichi", "un", "I", "\u4E00" },
				{ "1", "one", "ichi", "un", "I", "\u4E00" }, { "1", "one", "ichi", "un", "I", "\u4E00" },
				{ "1", "one", "ichi", "un", "I", "\u4E00" }

		};
		final String columnName[] = { "#", "English", "Japanese", "French", "Roman", "Kanji" };

		final TableModel fixedColumnModel = new AbstractTableModel() {

			@Override
			public int getRowCount() {
				return rowData.length;
			}

			@Override
			public int getColumnCount() {
				return 1;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return rowData[rowIndex][columnIndex];
			}
			public String getColumnName(int column) {
				return columnName[column];
			}

		};
		
		final TableModel mainModel = new AbstractTableModel() {

			@Override
			public int getRowCount() {
				return rowData.length;
			}

			@Override
			public int getColumnCount() {
				return columnName.length-1;
			}
			public String getColumnName(int column) {
				return columnName[column+1];
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return rowData[rowIndex][columnIndex+1];
			}
			
		};
		Runnable runner = new Runnable() {
			public void run() {
				JTable fixedTable = new JTable(fixedColumnModel);
				fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				JTable mainTable = new JTable(mainModel);
				mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				ListSelectionModel model = fixedTable.getSelectionModel();
				mainTable.setSelectionModel(model);
				
				JScrollPane pane = new JScrollPane(mainTable);
				Dimension fixedSize = fixedTable.getPreferredSize();
				JViewport view = new JViewport();
				view.setView(fixedTable);
				view.setPreferredSize(fixedSize);
				view.setMaximumSize(fixedSize);
				pane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixedTable.getTableHeader());
				pane.setRowHeaderView(view);
				JFrame frame = new JFrame("Fixed Column Table");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(pane, BorderLayout.CENTER); frame.setSize(300, 150);
				frame.setVisible(true);
				
			}
		};
		EventQueue.invokeLater(runner);
	}
}
