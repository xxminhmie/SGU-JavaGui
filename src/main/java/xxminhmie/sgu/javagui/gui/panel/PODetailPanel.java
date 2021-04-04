package xxminhmie.sgu.javagui.gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import xxminhmie.sgu.javagui.gui.common.AddButton;
import xxminhmie.sgu.javagui.gui.common.ChooseFileButton;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.modeltable.PODetailModelData;
import xxminhmie.sgu.javagui.model.PODetailModel;
import xxminhmie.sgu.javagui.service.impl.PODetailService;

public class PODetailPanel extends JPanel {
	PODetailService service;

	JPanel tfPanel = new JPanel();
	JTextField[] tfList = new JTextField[6];

	// Detail
	JPanel tablePanel = new JPanel();
	JScrollPane pane;
	JTable table;
	PODetailModelData model;

	PODetailModel selectedRow = new PODetailModel();
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to
																				// delete
	int selectedRowIndex;

	Long poId;
	Long skuId;

	// Button
	ChooseFileButton chooseFileBtn = new ChooseFileButton();

	JPanel btnPanel = new JPanel();
	AddButton addBtn;
	ResetButton resetBtn;
	DeleteButton deleteBtn;

	public PODetailPanel(PODetailService service) {
		this.service = service;
		this.setBackground(AbstractPanel.PanelBg);
		this.setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		this.setLayout(null);
		this.init();

	}

	protected void init() {

		/**********************************
		 * TEXT FIELD
		 **********************************/
		this.tfPanel.setLayout(null);
		this.tfPanel.setBounds(0, 0, 250, 230);
		this.add(this.tfPanel);

		String[] infoName = { "PO.ID", "SKU ID", "SupplierID", "Quantity", "Unit Price", "SubTotal" };

		int x = 0;
		int y = 20;
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			label.setAlignmentX(RIGHT_ALIGNMENT);
			this.tfPanel.add(label);
			this.tfList[i] = new JTextField();
			this.tfList[i].setText("");
			this.tfList[i].setBounds(x + 80, y + 5, 160, 20);
			this.tfPanel.add(this.tfList[i]);
			y += 30;

		}

		/** set read - only for ID text field **/
		this.tfList[0].setEditable(false);
		this.tfList[1].setEditable(false);
		this.tfList[0].setForeground(new Color(108, 108, 108));

		/*
		 * Table
		 */
		this.model = new PODetailModelData();
		this.table = new JTable(model);
		this.table.setDefaultRenderer(Object.class, new Renderer());

		model.setColumnWidth(table);

		/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {

					int rowIndex = table.getSelectedRow();// get index of selected rowIndex
					selectedRowIndex = rowIndex;
					/** GET MANUALLY ALL ROW **/
					selectedRow.setPoId((Long) table.getModel().getValueAt(rowIndex, 0));
					selectedRow.setSkuId((Long) table.getModel().getValueAt(rowIndex, 1));
					selectedRow.setSupplierId((Long) table.getModel().getValueAt(rowIndex, 2));
					selectedRow.setQuantity((Integer) table.getModel().getValueAt(rowIndex, 3));
					selectedRow.setUnitPrice((String) table.getModel().getValueAt(rowIndex, 4));
					selectedRow.setSubTotal((String) table.getModel().getValueAt(rowIndex, 5));
					poId = selectedRow.getPoId();
				}
				/** DISPLAY TO TEXT FIELD **/
				tfList[0].setText(selectedRow.getPoId().toString());
				tfList[1].setText(selectedRow.getSkuId().toString());
				tfList[2].setText(selectedRow.getSupplierId().toString());
				tfList[3].setText(String.valueOf(selectedRow.getQuantity()));
				tfList[4].setText(selectedRow.getUnitPrice());
				tfList[5].setText(selectedRow.getSubTotal());

				getAllSelectedRow(table);

//				loadProductInfo();
			}
		});
		/********************** INITIALIZE SCROLL PANE **************************/
		this.add(tablePanel);
		tablePanel.setBounds(0, 230, 1000, 500);
		tablePanel.setLayout(null);

		this.pane = new JScrollPane(this.table);
		this.pane.setBounds(0, 20, 520, 340);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		this.tablePanel.add(this.pane);

		// Button
		btnPanel.setBounds(250, 0, 700, 230);
		btnPanel.setLayout(null);
//		btnPanel.setBackground(Color.BLUE);
		this.add(btnPanel);

		addBtn = new AddButton(20, 20);
		btnPanel.add(addBtn);

		resetBtn = new ResetButton(20, 50);
		btnPanel.add(resetBtn);

		deleteBtn = new DeleteButton(20, 80);
		btnPanel.add(deleteBtn);

	}

	/*
	 * Table listerner handle
	 */
	public void getAllSelectedRow(JTable entryTable) {
//		AbstractTableModel model = (AbstractTableModel) entryTable.getModel();
		if (entryTable.getRowCount() > 0) {
			if (entryTable.getSelectedRowCount() > 0) {
				int selectedRow[] = entryTable.getSelectedRows();
				idSelectedRowList.clear();
				for (int i : selectedRow) {
					Long id = (Long) entryTable.getValueAt(i, 0);
					idSelectedRowList.add(id);
				}
			}
		}
	}


	public void loadData(String str) {
		this.model.loadData(table, str);

	}

	public void resetButtonHandle() {
		int click = JOptionPane.showConfirmDialog(null, "Are you sure to reset?");
		if (click == JOptionPane.YES_OPTION) {
			resetSku();
		}

	}

	public void resetSku() {
		for (int i = 0; i < this.tfList.length; i++) {
			this.tfList[i].setText("");
		}
	}

	/*
	 * Button handle
	 */
	public int addButtonHandle(Long productId) {
//		Long id = null;
//		if (this.tfList[0].getText().isBlank() == false) {
//			try {
//				id = Long.parseLong(tfList[0].getText());
//			} catch (NumberFormatException e) {
//
//			}
//		}
//
//		String color = null;
//		if (this.tfList[2].getText().isBlank() == false) {
//			color = this.tfList[2].getText();
//		} else {
//			JOptionPane.showMessageDialog(null, "Product's color must be not null!");
//			tfList[2].requestFocus();
//			return -1;
//		}
//
//		String size = null;
//		if (comboSize.getSelectedItem() != null) {
//			size = comboSize.getSelectedItem().toString();
//		} else {
//			JOptionPane.showMessageDialog(null, "Product's size must be not null!");
//			comboSize.requestFocus();
//			return -1;
//		}
//		int qty = 0;
//		if (this.tfList[5].getText().isBlank() == false) {
//			try {
//				qty = Integer.parseInt(tfList[4].getText());
//			} catch (NumberFormatException e) {
//				JOptionPane.showMessageDialog(null, "Product's quantity is invalid!");
//				tfList[4].requestFocus();
//				return -1;
//			}
//		}
//
//		String price = null;
//		if (this.tfList[5].getText().isBlank() == false) {
//			try {
//				int temp = Integer.parseInt(tfList[4].getText());
//				if (temp != 0 && temp <= 1000) {
//					price = tfList[4].getText() + "000";
//				}
//			} catch (NumberFormatException e) {
//				JOptionPane.showMessageDialog(null, "Product's price is invalid!");
//				tfList[5].requestFocus();
//				return -1;
//			}
//		}
//
//		String status = null;
//		if (comboStatus.getSelectedItem() != null) {
//			status = comboStatus.getSelectedItem().toString();
//			if (status.equals("Actived") == false) {
//				JOptionPane.showMessageDialog(null, "Product's status must be 'actived' while adding!");
//				tfList[5].requestFocus();
//				return -1;
//			}
//		}

		return 0;

	}

}
