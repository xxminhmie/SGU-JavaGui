package JTable.CustomTableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import xxminhmie.sgu.javagui.model.CustomerModel;
import xxminhmie.sgu.javagui.service.impl.CustomerService;

public class ModelData extends AbstractTableModel{
	CustomerService cs = new CustomerService();
//    List<CustomerModel> data = new ArrayList<CustomerModel>();
    List<CustomerModel> data = cs.findAll();

    String colNames[] = { "ID", "FullName", "Phone" };


	@Override
	public int getRowCount() {
        return data.size();
	}

	@Override
	public int getColumnCount() {
        return colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
            return data.get(rowIndex).getId();
        }
        if (columnIndex == 1) {
            return data.get(rowIndex).getFullName();
        }
        if (columnIndex == 2) {
            return data.get(rowIndex).getPhone();
        }
        return null;
	}
	
	 public String getColumnName(int columnIndex) {
	        return colNames[columnIndex];
	    }
//	 public Class<?> getColumnClass(int columnIndex) {
//	        return colClass[columnIndex];
//	    }

	 public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return true;
	    }
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	        if (columnIndex == 0) {
	            data.get(rowIndex).setId((Long) aValue);
	        }
	        if (columnIndex == 1) {
	            data.get(rowIndex).setFullName((String) aValue);
	        }
	        if (columnIndex == 2) {
	            data.get(rowIndex).setPhone((String) aValue);
	        }
	        fireTableCellUpdated(rowIndex, columnIndex);
	    }
}
