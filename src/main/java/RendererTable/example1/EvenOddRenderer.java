package RendererTable.example1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class EvenOddRenderer implements TableCellRenderer {

	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
				column);
		Color foreground, background;
		if (isSelected) {
			foreground = Color.YELLOW;
			background = Color.GREEN;
		} else {
			if (row % 2 == 0) {
				foreground = Color.BLUE;
				background = Color.WHITE;
			} else {
				foreground = Color.WHITE;
				background = Color.BLUE;
			}
		}
		renderer.setForeground(foreground);
		renderer.setBackground(background);
		return renderer;
	}
}
