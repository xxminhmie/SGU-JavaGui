package JTable.EditableColorColumn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


public class EditableColorColumn {
	public static void main(String args[]) {
		Runnable runner = new Runnable() {
			public void run() {
				Color choices[] = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
				JComboBox comboBox = new JComboBox(choices);
				ComboTableCellRenderer renderer = new ComboTableCellRenderer();
				
				comboBox.setRenderer(renderer);
				
				TableCellEditor editor = new DefaultCellEditor(comboBox);
				
				JFrame frame = new JFrame("Editable Color Table");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				TableModel model = new ColorTableModel();
				JTable table = new JTable(model);
				
				TableColumn column = table.getColumnModel().getColumn(3);
				column.setCellRenderer(renderer);
				column.setCellEditor(editor);

				JScrollPane scrollPane = new JScrollPane(table);
				frame.add(scrollPane, BorderLayout.CENTER);
				frame.setSize(400, 150);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}

}
