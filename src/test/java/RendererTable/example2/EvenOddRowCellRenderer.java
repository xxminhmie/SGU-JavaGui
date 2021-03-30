package RendererTable.example2;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EvenOddRowCellRenderer extends JFrame {
	DefaultTableModel tmodel = new DefaultTableModel(new Object[][] { { "some", "text" }, { "any", "text" },
			{ "even", "more" }, { "text", "strings" }, { "and", "other" }, { "text", "values" } },
			new Object[] { "Column 1", "Column 2" });

	public EvenOddRowCellRenderer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTable table = new JTable(tmodel);
		table.setDefaultRenderer(Object.class, new MyRenderer1());
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		pack();
	}

	public static void main(String arg[]) {
		new EvenOddRowCellRenderer().setVisible(true);
	}
}

