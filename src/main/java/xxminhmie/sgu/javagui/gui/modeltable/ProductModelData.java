package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.service.impl.ProductService;

public class ProductModelData extends AbstractTableModel{
	ProductService service = new ProductService();
	List<ProductModel> data = service.findAll();
	String columnNames[] = {"ID", "Name", "Brand","Des.", "Status"};

	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	public ProductService getService() {
		return this.service;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return data.get(rowIndex).getId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getName();
		}
		if (columnIndex == 2) {
			return data.get(rowIndex).getBrand();
		}
		if (columnIndex == 3) {
			return data.get(rowIndex).getDescription();
		}
		if (columnIndex == 4) {
			return data.get(rowIndex).getStatus();
		}
		
		return null;
	}
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			data.get(rowIndex).setId((Long) aValue);
		}
		if (columnIndex == 1) {
			data.get(rowIndex).setName((String) aValue);
		}
		if (columnIndex == 2) {
			data.get(rowIndex).setBrand((String) aValue);
		}
		if (columnIndex == 3) {
			data.get(rowIndex).setDescription((String) aValue);
		}
		if (columnIndex == 4) {
			data.get(rowIndex).setStatus((String) aValue);
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	public ProductModel getProductModel(int rowIndex) {
		return data.get(rowIndex);
	}
	//refresh data from database
	public void loadData(JTable table) {
		this.data = this.service.findAll();
	    fireTableChanged(null);
	    this.setColumnWidth(table);
	}
	//refresh data from list
	public void loadData(JTable table, String str) {
		this.data = this.service.search(str);
	    fireTableChanged(null);
	    this.setColumnWidth(table);

	}
	public void setColumnWidth(JTable table) {
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(40);
		columnModel.getColumn(1).setPreferredWidth(220);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(60);
		columnModel.getColumn(4).setPreferredWidth(160);
	}


}
