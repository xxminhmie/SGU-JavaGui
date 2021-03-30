package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import xxminhmie.sgu.javagui.model.CustomerModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.service.impl.SkuService;

public class SkuModelData extends AbstractTableModel {
	SkuService service = new SkuService();
	List<SkuModel> data = service.findAll();

	String columnNames[] = { "ID", "P.ID", "Color", "Size", "Q.ty", "Price (vnd)", "Im.Price (vnd)", "Status", "Image" };

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public SkuService getService() {
		return this.service;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return data.get(rowIndex).getId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getProductId();
		}
		if (columnIndex == 2) {
			return data.get(rowIndex).getColor();
		}
		if (columnIndex == 3) {
			return data.get(rowIndex).getSize();
		}
		if (columnIndex == 4) {
			return data.get(rowIndex).getQuantity();
		}
		if (columnIndex == 5) {
			return data.get(rowIndex).getPrice();
		}
		if (columnIndex == 6) {
			return data.get(rowIndex).getImportPrice();
		}
		if (columnIndex == 7) {
			return data.get(rowIndex).getStatus();
		}
		if (columnIndex == 8) {
			return data.get(rowIndex).getImage();
		}
		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			data.get(rowIndex).setId((Long) aValue);
		}
		if (columnIndex == 1) {
			data.get(rowIndex).setProductId((Long) aValue);
		}
		if (columnIndex == 2) {
			data.get(rowIndex).setColor((String) aValue);
		}
		if (columnIndex == 3) {
			data.get(rowIndex).setSize((String) aValue);
		}
		if (columnIndex == 4) {
			data.get(rowIndex).setQuantity((Integer) aValue);
		}
		if (columnIndex == 5) {
			data.get(rowIndex).setPrice((String) aValue);
		}
		if (columnIndex == 6) {
			data.get(rowIndex).setImportPrice((String) aValue);
		}
		if (columnIndex == 7) {
			data.get(rowIndex).setStatus((String) aValue);
		}
		if (columnIndex == 8) {
			data.get(rowIndex).setImage((String) aValue);
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public SkuModel getSkuModel(int rowIndex) {
		return data.get(rowIndex);
	}

	// refresh data from database
	public void loadData(JTable table) {
		this.data = this.service.findAll();
		fireTableChanged(null);
		setColumnWidth(table);
	}

	// refresh data from list
	public void loadData(JTable table, String str) {
		this.data = this.service.search(str);
		fireTableChanged(null);
		setColumnWidth(table);

	}

	// refresh data from row selected product
	public void loadData(JTable table, Long productId) {
		this.data = this.service.findByProductId(productId);
		fireTableChanged(null);
		setColumnWidth(table);
	}
	
	public void setColumnWidth(JTable table) {
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(40);
		columnModel.getColumn(2).setPreferredWidth(120);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);
		columnModel.getColumn(5).setPreferredWidth(160);
		columnModel.getColumn(6).setPreferredWidth(160);
		columnModel.getColumn(7).setPreferredWidth(60);
	}

}
