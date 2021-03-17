package xxminhmie.sgu.javagui.gui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class Renderer implements TableCellRenderer {
	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component renderer = 
				DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Color bg;
		if(isSelected) {
			bg = new Color(130,130,130);
		}else {
			if (row%2 == 0) {
				bg = new Color(230, 230, 230);
			} else {
				bg = Color.WHITE;
			}
		}
		renderer.setBackground(bg);
		return renderer;
		
//		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
//				column);
//		Color foreground, background;
//		if (isSelected == true) {
//			foreground = Color.YELLOW;
//			background = Color.GREEN;
//		} else {
//			if (row % 2 == 0) {
//				foreground = Color.BLUE;
//				background = Color.WHITE;
//			} else {
//				foreground = Color.WHITE;
//				background = Color.BLUE;
//			}
//		}
//		renderer.setForeground(foreground);
//		renderer.setBackground(background);
//		return renderer;
	}

}
