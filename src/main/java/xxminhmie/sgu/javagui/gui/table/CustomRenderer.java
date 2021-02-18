package xxminhmie.sgu.javagui.gui.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomRenderer extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent
    (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(row%2 == 0 ){
            cellComponent.setBackground(Color.GRAY);
        } else {
            cellComponent.setBackground(Color.CYAN);
        }
        return cellComponent;
    }

    
}
