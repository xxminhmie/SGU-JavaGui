package RendererTable.example1;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ResizeTable {
	public static void main(String args[]) {

		final Object rowData[][] = { 
				{ "1", "one", "I" }, 
				{ "1", "one", "I" }, 
				{ "1", "one", "I" }, 
				{ "1", "one", "I" }, 

				{ "2", "two", "II" }, 
				{ "3", "three", "III" }
				};
		final String columnNames[] = { "#", "English", "Roman" };

		final JTable table = new JTable(rowData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);

		table.setDefaultRenderer(Object.class, new EvenOddRenderer());

		JFrame frame = new JFrame("Resizing Table");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(scrollPane, BorderLayout.CENTER);

		frame.setSize(300, 150);
		frame.setVisible(true);

	}
}