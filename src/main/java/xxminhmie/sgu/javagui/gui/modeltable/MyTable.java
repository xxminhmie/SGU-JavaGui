package xxminhmie.sgu.javagui.gui.modeltable;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class MyTable extends JTable{
	public MyTable(AbstractTableModel model) {
		this.setModel(model);
	}
	

}
